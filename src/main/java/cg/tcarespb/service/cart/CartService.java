package cg.tcarespb.service.cart;

import cg.tcarespb.models.*;
import cg.tcarespb.models.enums.*;
import cg.tcarespb.repository.*;
import cg.tcarespb.service.addInfo.AddInfoService;
import cg.tcarespb.service.cart.request.*;
import cg.tcarespb.service.cart.response.*;
import cg.tcarespb.service.cartInfo.CartInfoService;
import cg.tcarespb.service.cartSkill.CartSkillService;
import cg.tcarespb.service.dateSession.DateSessionService;
import cg.tcarespb.service.employee.response.EmployeeFilterResponse;
import cg.tcarespb.service.historyWorking.HistoryWorkingService;
import cg.tcarespb.service.location.LocationPlaceService;
import cg.tcarespb.service.serviceGeneral.ServiceGeneralService;
import cg.tcarespb.service.skill.SkillService;
import cg.tcarespb.util.AppMessage;
import cg.tcarespb.util.AppUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ServiceGeneralService serviceGeneralService;
    private final DateSessionService dateSessionService;
    private final LocationPlaceService locationPlaceService;
    private final CartSkillService cartSkillService;
    private final SkillService skillService;
    private final CartInfoService cartInfoService;
    private final AddInfoService addInfoService;
    private final EmployeeRepository employeeRepository;
    private final HistoryWorkingService historyWorkingService;
    private final DateSessionRepository dateSessionRepository;
    private final HistoryWorkingRepository historyWorkingRepository;
    private final LocationPalaceRepository locationPalaceRepository;
    private final SalerRepository salerRepository;
    private final CartSkillRepository cartSkillRepository;
    private final CartInfoRepository cartInfoRepository;


    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> getAllByIdUser(String userId) {
        return cartRepository.findAllByUserId(userId);
    }

    public Cart findById(String id) {
        return cartRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Cart", id)));
    }

    public CartAllFieldResponse findCartById(String id) {
        Cart cart = findById(id);
        CartAllFieldResponse cartResponse = new CartAllFieldResponse();
        cartResponse.setId(cart.getId());
        cartResponse.setTimeStart(cart.getTimeStart());
        cartResponse.setTimeEnd(cart.getTimeEnd());
        cartResponse.setAgePatient(cart.getAgePatient());
        cartResponse.setNoteForEmployee(cart.getNoteForEmployee());
        cartResponse.setNoteForPatient(cart.getNoteForPatient());
        cartResponse.setNoteForPatient(cart.getNoteForPatient());
        cartResponse.setFirstName(cart.getFirstName());
        cartResponse.setLastName(cart.getLastName());
        cartResponse.setSaleNote(cart.getSaleNote());
        cartResponse.setPhone(cart.getPhone());
        cartResponse.setMemberOfFamily(cart.getMemberOfFamily());
        cartResponse.setDecade(cart.getEDecade());
        cartResponse.setGender(cart.getGender());

        List<CartSkillInfoServiceResponse> infoList = new ArrayList<>();
        for (var e : cart.getCartInfos()) {
            CartSkillInfoServiceResponse info = new CartSkillInfoServiceResponse();
            info.setId(e.getAddInfo().getId());
            info.setName(e.getAddInfo().getName());
            infoList.add(info);
        }
        cartResponse.setInfoList(infoList);

        List<CartSkillInfoServiceResponse> skillList = new ArrayList<>();
        for (var e : cart.getCartSkills()) {
            CartSkillInfoServiceResponse skill = new CartSkillInfoServiceResponse();
            skill.setId(e.getSkill().getId());
            skill.setName(e.getSkill().getName());
            skillList.add(skill);
        }
        cartResponse.setInfoList(skillList);

        CartSkillInfoServiceResponse service = new CartSkillInfoServiceResponse();
        service.setId(cart.getService().getId());
        service.setName(cart.getService().getName());
        service.setDesciption(cart.getService().getDescription());
        cartResponse.setService(service);

        CartLocationPlaceRepsonse location = new CartLocationPlaceRepsonse();
        location.setName(cart.getLocationPlace().getName());
        location.setDistanceForWork(cart.getLocationPlace().getDistanceForWork());
        location.setLongitude(cart.getLocationPlace().getLongitude());
        location.setLatitude(cart.getLocationPlace().getLatitude());
        cartResponse.setLocationPlace(location);

        CartContactEmployeeResponse contactEmployeeResponse = new CartContactEmployeeResponse();
        ContactEmployee contactEmployee = cart.getContactEmployees();
        if (contactEmployee != null) {
            contactEmployeeResponse.setContactStatus(contactEmployee.getContactStatus());
            contactEmployeeResponse.setIdEmployee(contactEmployee.getEmployee().getId());
            contactEmployeeResponse.setLastName(contactEmployee.getEmployee().getLastName());
            contactEmployeeResponse.setFirstName(contactEmployee.getEmployee().getFirstName());
            contactEmployeeResponse.setFee(contactEmployee.getFee());
            contactEmployeeResponse.setDateTime(contactEmployee.getDateTime());
            cartResponse.setContactEmployee(contactEmployeeResponse);
        }

        List<CartDateSessionResponse> dateSessionResponseList = new ArrayList<>();
        for (var e : dateSessionRepository.findAllByCartId(id)) {
            CartDateSessionResponse dateSessionResponse = new CartDateSessionResponse();
            dateSessionResponse.setSessionOfDate(e.getSessionOfDate());
            dateSessionResponse.setDateInWeek(e.getDateInWeek());
            dateSessionResponseList.add(dateSessionResponse);
        }
        cartResponse.setDateSessionResponseList(dateSessionResponseList);

        List<CartHistoryWorkingResponse> historyWorkingResponseList = new ArrayList<>();
        for (var e : historyWorkingRepository.findAllByCartId(id)) {
            CartHistoryWorkingResponse historyWorkingResponse = new CartHistoryWorkingResponse();
            historyWorkingResponse.setSessionOfDate(e.getSessionOfDate());
            historyWorkingResponse.setDateInWeek(e.getDateInWeek());
            historyWorkingResponse.setDateWork(e.getDateWork());
            historyWorkingResponseList.add(historyWorkingResponse);
        }
        cartResponse.setHistoryWorkingResponseList(historyWorkingResponseList);

        return cartResponse;
    }

    public void updateCartService(CartServiceListSaveRequest req, String cartId) {
        Cart cart = findById(cartId);
        ServiceGeneral serviceGeneral = serviceGeneralService.findById(req.getServiceId());
        cart.setService(serviceGeneral);
        cartRepository.save(cart);
    }

    @Transactional
    public void updateDateSessionCart(CartDateSessionListSaveRequest req, String cartId) {
        dateSessionRepository.deleteAllByCartId(cartId);
        historyWorkingRepository.deleteAllByCartId(cartId);
        Cart cart = findById(cartId);
        cart.setTimeStart(null);
        cart.setTimeEnd(null);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(req.getTimeStart(), dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(req.getTimeEnd(), dateTimeFormatter);
        cart.setTimeStart(startDate);
        cart.setTimeEnd(endDate);
        List<DateSession> dateSessionList = new ArrayList<>();
        for (var dateSession : req.getListDateSession()) {
            EDateInWeek date = EDateInWeek.valueOf(dateSession.getDate());
            for (var sessionOfDate : dateSession.getSessionOfDateList()) {
                ESessionOfDate sessionDate = ESessionOfDate.valueOf(sessionOfDate);
                DateSession newDateSession = new DateSession();
                newDateSession.setSessionOfDate(sessionDate);
                newDateSession.setDateInWeek(date);
                newDateSession.setCart(cart);
                dateSessionList.add(newDateSession);
                dateSessionService.create(newDateSession);
            }
        }
        cart.setDateSessions(dateSessionList);
        historyWorkingService.createHistoryWorkingForCart(cart);
        cartRepository.save(cart);
    }


    public void updateInfoPatient(CartInfoPatientSaveRequest req, String cardId) {
        Cart cart = findById(cardId);
        cart.setNoteForPatient(req.getNoteForPatient());
        cart.setGender(EGender.valueOf(req.getGender()));
        cart.setEDecade(EDecade.valueOf(req.getDecade()));
        cart.setMemberOfFamily(EMemberOfFamily.valueOf(req.getMemberOfFamily()));
        cartRepository.save(cart);
    }

    public void updateNoteForEmployee(CartNoteForEmployeeSaveRequest req, String cardId) {
        Cart cart = findById(cardId);
        cart.setNoteForEmployee(req.getNoteForEmployee());
        cartRepository.save(cart);
    }

    public void updateLocationForCart(CartLocationSaveRequest req, String cardId) {
        Cart cart = findById(cardId);
        LocationPlace locationPlace = new LocationPlace();
        locationPlace.setName(req.getNameLocation());
        locationPlace.setDistanceForWork(Double.valueOf(req.getDistanceForWork()));
        locationPlace.setLatitude(Double.valueOf(req.getLatitude()));
        locationPlace.setLongitude(Double.valueOf(req.getLongitude()));
        locationPlaceService.create(locationPlace);
        cart.setLocationPlace(locationPlace);
        cartRepository.save(cart);
    }

    public void updateCartSkill(CartSkillSaveRequest req, String cartId) {
        Cart cart = findById(cartId);
        cartSkillRepository.deleteAllByCartId(cartId);
        List<CartSkill> cartSkillList = new ArrayList<>();
        for (var skillElemId : req.getCartSkillIdList()) {
            Skill skill = skillService.findByIdForEdit(skillElemId);
            CartSkill cartSkill = new CartSkill();
            cartSkill.setSkill(skill);
            cartSkill.setCart(cart);
            cartSkillService.create(cartSkill);
            cartSkillList.add(cartSkill);
        }
        cart.setCartSkills(cartSkillList);
        cartRepository.save(cart);
    }

    public void updateCartInfo(CartInfoSaveRequest req, String cartId) {
        Cart cart = findById(cartId);
        cartInfoRepository.deleteAllByCartId(cartId);
        List<CartInfo> cartInfoList = new ArrayList<>();
        for (var infoElemId : req.getInfoIdList()) {
            AddInfo info = addInfoService.findByIdForEdit(infoElemId);
            CartInfo cartInfo = new CartInfo();
            cartInfo.setAddInfo(info);
            cartInfo.setCart(cart);
            cartInfoService.create(cartInfo);
            cartInfoList.add(cartInfo);
        }
        cart.setCartInfos(cartInfoList);
        cartRepository.save(cart);
    }

    public void updateTimeStartEnd(CartTimeStartEndSaveRequest req, String cardId) {
        Cart cart = findById(cardId);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(req.getTimeStart(), dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(req.getTimeEnd(), dateTimeFormatter);
        cart.setTimeStart(startDate);
        cart.setTimeEnd(endDate);
        cartRepository.save(cart);
    }

    public String createCartForFilter(CartSaveFilterRequest req) {
        Cart cart = new Cart();
        cartRepository.save(cart);
        cart.setService(serviceGeneralService.findById(req.getService()));

        cart.setTimeStart(LocalDate.parse(req.getTimeStart(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        cart.setTimeEnd(LocalDate.parse(req.getTimeEnd(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        LocationPlace locationPalace = new LocationPlace();
        locationPalace.setName(req.getNameLocation());
        locationPalace.setDistanceForWork(Double.valueOf(req.getDistanceForWork()));
        locationPalace.setLatitude(Double.valueOf(req.getLatitude()));
        locationPalace.setLongitude(Double.valueOf(req.getLongitude()));
        locationPlaceService.create(locationPalace);
        cart.setLocationPlace(locationPalace);

        List<CartInfo> cartInfoList = new ArrayList<>();
        for (var infoElemId : req.getListInfoId()) {
            AddInfo info = addInfoService.findByIdForEdit(infoElemId);
            CartInfo cartInfo = new CartInfo();
            cartInfo.setAddInfo(info);
            cartInfo.setCart(cart);
            cartInfoService.create(cartInfo);
            cartInfoList.add(cartInfo);
        }
        cart.setCartInfos(cartInfoList);

        List<CartSkill> cartSkillList = new ArrayList<>();
        for (var skillElemId : req.getListSkillId()) {
            Skill skill = skillService.findByIdForEdit(skillElemId);
            CartSkill cartSkill = new CartSkill();
            cartSkill.setSkill(skill);
            cartSkill.setCart(cart);
            cartSkillService.create(cartSkill);
            cartSkillList.add(cartSkill);
        }
        cart.setCartSkills(cartSkillList);

        List<DateSession> dateSessionList = new ArrayList<>();
        for (var dateSession : req.getListDateSession()) {
            EDateInWeek date = EDateInWeek.valueOf(dateSession.getDate());
            for (var sessionOfDate : dateSession.getSessionOfDateList()) {
                ESessionOfDate sessionDate = ESessionOfDate.valueOf(sessionOfDate);
                DateSession newDateSession = new DateSession();
                newDateSession.setSessionOfDate(sessionDate);
                newDateSession.setDateInWeek(date);
                newDateSession.setCart(cart);
                dateSessionList.add(newDateSession);
                dateSessionService.create(newDateSession);
            }
        }
        cart.setDateSessions(dateSessionList);
        cart.setHistoryWorking(historyWorkingService.createHistoryWorkingForCart(cart));
        return cartRepository.save(cart).getId();

    }


    public Page<EmployeeFilterResponse> filter(String idCart, Pageable pageable) {
        Cart cart = findById(idCart);
        CartSkillFilterRequest req = new CartSkillFilterRequest();
        req.setCartSkillIdList(cart.getCartSkills().stream().map(e -> e.getSkill().getId()).collect(Collectors.joining(",")));
        CartFilterRequest request = new CartFilterRequest();
        request.setCartServiceId(cart.getService().getId());
        request.setCartSkillIdList(cart.getCartSkills().stream().map(e -> e.getSkill().getId()).collect(Collectors.joining(",")));
        request.setCartInfoIdList(cart.getCartInfos().stream().map(e -> e.getAddInfo().getId()).collect(Collectors.joining(",")));
        request.setDistance(cart.getLocationPlace().getDistanceForWork());
        request.setLatitude(cart.getLocationPlace().getLatitude());
        request.setLongitude(cart.getLocationPlace().getLongitude());
        request.setNameLocation(cart.getLocationPlace().getName());
        request.setStatus(EStatus.ACTIVE);
        Page<EmployeeFilterResponse> employeeList = employeeRepository.filter(request, pageable);
        employeeList.stream().forEach(e -> e.setDistanceToWork(locationPlaceService.getDistance(request.getLatitude(), request.getLongitude(), e.getLatitude(), e.getLongitude())));

        for (var e : employeeList) {
            Employee employee = employeeRepository.findById(e.getId()).orElse(null);
            Photo photo = employee.getPhoto();
            if (photo == null) {
                e.setPhotoUrl("abc");
            } else {
                e.setPhotoUrl(photo.getUrl());
            }
            e.setSkillName(employee.getEmployeeSkills().stream().map(elem -> elem.getSkill().getName()).collect(Collectors.toList()));
            e.setInfoName(employee.getEmployeeInfos().stream().map(elem -> elem.getAddInfo().getName()).collect(Collectors.toList()));
            e.setServiceName(employee.getEmployeeServiceGenerals().stream().map(elem -> elem.getService().getName()).collect(Collectors.toList()));
            List<Rate> rateList = employee.getRates();
            if (rateList.size() == 0) {
                e.setRateQuantity(5);
                e.setStarAverage(5F);
            } else {
                Float totalStar = 0F;
                for (var rate : rateList) {
                    totalStar += rate.getStarQuantity();
                }
                e.setStarAverage(totalStar / rateList.size());
                e.setRateQuantity(rateList.size());
            }
        }
        return employeeList;
    }

    //    public List<String> filterTest(String idCart) {
//        Cart cart = findById(idCart);
//        CartSkillFilterRequest req = new CartSkillFilterRequest();
//        req.setCartSkillIdList(cart.getCartSkills().stream().map(e -> e.getSkill().getId()).collect(Collectors.joining(",")));
//        CartFilterRequest request = new CartFilterRequest();
//        request.setCartServiceId(cart.getService().getId());
//        request.setCartSkillIdList(cart.getCartSkills().stream().map(e -> e.getSkill().getId()).collect(Collectors.joining(",")));
//        request.setCartInfoIdList(cart.getCartInfos().stream().map(e -> e.getAddInfo().getId()).collect(Collectors.joining(",")));
//        request.setDistance(cart.getLocationPlace().getDistanceForWork());
//        request.setLatitude(cart.getLocationPlace().getLatitude());
//        request.setLongitude(cart.getLocationPlace().getLongitude());
//        request.setStatus(EStatus.ACTIVE);
//        List<String> employeeList = employeeRepository.filterTest(request);
//        return employeeList;
//    }
//    public List<String> filterTest(String idCart) {
//        Cart cart = findById(idCart);
//        CartSkillFilterRequest req = new CartSkillFilterRequest();
//        req.setCartSkillIdList(cart.getCartSkills().stream().map(e -> e.getSkill().getId()).collect(Collectors.joining(",")));
//        CartFilterRequest request = new CartFilterRequest();
//        request.setCartServiceId(cart.getService().getId());
//        request.setCartSkillIdList(cart.getCartSkills().stream().map(e -> e.getSkill().getId()).collect(Collectors.joining(",")));
//        request.setCartInfoIdList(cart.getCartInfos().stream().map(e -> e.getAddInfo().getId()).collect(Collectors.joining(",")));
//        request.setDistance(cart.getLocationPlace().getDistanceForWork());
//        request.setLatitude(cart.getLocationPlace().getLatitude());
//        request.setLongitude(cart.getLocationPlace().getLongitude());
//        request.setStatus(EStatus.ACTIVE);
//        List<String> employeeList = employeeRepository.filterTest(request);
//        return employeeList;
//    }

    public List<CartListResponse> findCartBySaler(String id) {
        Optional<Saler> saler = salerRepository.findById(id);
        Saler saler1 = saler.get();
        return saler1.getCarts().stream().map(
                        service -> CartListResponse.builder()
                                .id(service.getId())
                                .timeStart(service.getTimeStart() != null ? String.valueOf(service.getTimeStart()) : "")
                                .timeEnd(service.getTimeEnd() != null ? String.valueOf(service.getTimeEnd()) : "")
                                .noteForPatient(service.getNoteForPatient())
                                .noteForEmployee(service.getNoteForEmployee())
                                .memberOfFamily(String.valueOf(service.getMemberOfFamily()))
                                .gender(String.valueOf(service.getGender()))
                                .eDecade(String.valueOf(service.getEDecade()))
                                .firstName(service.getFirstName())
                                .lastName(service.getLastName())
                                .saleNote(service.getSaleNote())
                                .phone(service.getPhone())
                                .serviceGeneral(service.getService().getName() != null ? service.getService().getName() : "")
                                .locationPlace(service.getLocationPlace() != null ? service.getLocationPlace().getName() : "")
                                .build())
                .collect(Collectors.toList());
    }

    public void createCartBySale(CartSaveRequest request, String id) {
        var cart = AppUtil.mapper.map(request, Cart.class);
        Optional<Saler> saler = salerRepository.findById(id);
        Saler saler1 = saler.get();
        cart.setSaler(saler1);
        LocationPlace locationPalace = new LocationPlace();
        locationPalace.setName(request.getLocationPlace());
        locationPalace.setDistanceForWork(Double.valueOf(request.getDistanceForWork()));
        locationPalace.setLatitude(Double.valueOf(request.getLatitude()));
        locationPalace.setLongitude(Double.valueOf(request.getLongitude()));
        locationPalaceRepository.save(locationPalace);
        cart.setLocationPlace(locationPalace);
        cartRepository.save(cart);
    }

    public void updateAllFieldCart(CartAllFieldRequest req, String cartId) {
        Cart cart = findById(cartId);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (req.getTimeStart() != null
                && req.getTimeEnd() != null
                && !req.getTimeEnd().isEmpty()
                && !req.getTimeStart().isEmpty()) {
            LocalDate startDate = LocalDate.parse(req.getTimeStart(), dateTimeFormatter);
            LocalDate endDate = LocalDate.parse(req.getTimeEnd(), dateTimeFormatter);
            cart.setTimeStart(startDate);
            cart.setTimeEnd(endDate);
            if (req.getListDateSession().size() != 0) {
                dateSessionRepository.deleteAllByCartId(cartId);
                historyWorkingRepository.deleteAllByCartId(cartId);
                List<DateSession> dateSessionList = new ArrayList<>();
                for (var dateSession : req.getListDateSession()) {
                    EDateInWeek date = EDateInWeek.valueOf(dateSession.getDate());
                    for (var sessionOfDate : dateSession.getSessionOfDateList()) {
                        ESessionOfDate sessionDate = ESessionOfDate.valueOf(sessionOfDate);
                        DateSession newDateSession = new DateSession();
                        newDateSession.setSessionOfDate(sessionDate);
                        newDateSession.setDateInWeek(date);
                        newDateSession.setCart(cart);
                        dateSessionList.add(newDateSession);
                        dateSessionService.create(newDateSession);
                    }
                }
                cart.setDateSessions(dateSessionList);
                historyWorkingService.createHistoryWorkingForCart(cart);
            }
        }
        if (req.getService() != null && !req.getService().isEmpty()) {
            ServiceGeneral serviceGeneral = serviceGeneralService.findById(req.getService());
            cart.setService(serviceGeneral);
        }
        if (req.getListInfoId() != null && !req.getListInfoId().isEmpty()) {
            cartInfoRepository.deleteAllByCartId(cartId);
            List<CartInfo> cartInfoList = new ArrayList<>();
            for (var infoElemId : req.getListInfoId()) {
                AddInfo info = addInfoService.findByIdForEdit(infoElemId);
                if (info != null) { // Make sure the info exists
                    CartInfo cartInfo = new CartInfo();
                    cartInfo.setAddInfo(info);
                    cartInfo.setCart(cart);
                    cartInfoService.create(cartInfo);
                    cartInfoList.add(cartInfo);
                }
            }
            cart.setCartInfos(cartInfoList);
        }
        if (req.getListSkillId() != null && !req.getListSkillId().isEmpty()) {
            cartSkillRepository.deleteAllByCartId(cartId);
            List<CartSkill> cartSkillList = new ArrayList<>();
            for (var skillElemId : req.getListSkillId()) {
                Skill skill = skillService.findByIdForEdit(skillElemId);
                if (skill != null) { // Make sure the skill exists
                    CartSkill cartSkill = new CartSkill();
                    cartSkill.setSkill(skill);
                    cartSkill.setCart(cart);
                    cartSkillService.create(cartSkill);
                    cartSkillList.add(cartSkill);
                }
            }
            cart.setCartSkills(cartSkillList);
        }

        if (req.getLatitude() != null && !req.getLatitude().isEmpty()
                && req.getLongitude() != null && !req.getLongitude().isEmpty()
                && req.getNameLocation() != null && !req.getNameLocation().isEmpty()
                && req.getDistanceForWork() != null && !req.getDistanceForWork().isEmpty()) {

            LocationPlace locationPlace = new LocationPlace();
            locationPlace.setName(req.getNameLocation());
            locationPlace.setDistanceForWork(Double.valueOf(req.getDistanceForWork()));
            locationPlace.setLatitude(Double.valueOf(req.getLatitude()));
            locationPlace.setLongitude(Double.valueOf(req.getLongitude()));
            locationPlaceService.create(locationPlace);
            cart.setLocationPlace(locationPlace);

        } else if (((req.getLatitude() == null || req.getLatitude().isEmpty())
                || (req.getLongitude() == null || req.getLongitude().isEmpty())
                || (req.getNameLocation() == null || req.getNameLocation().isEmpty()))
                && (req.getDistanceForWork() != null && !req.getDistanceForWork().isEmpty())) {

            LocationPlace locationPlace = cart.getLocationPlace();
            locationPlace.setDistanceForWork(Double.valueOf(req.getDistanceForWork()));
            locationPlaceService.create(locationPlace);
        }
        cartRepository.save(cart);
    }
    public Page<EmployeeFilterResponse> createAndFilterCart(CartAllFieldRequest req, Pageable pageable){
        Cart cart = new Cart();
        cartRepository.save(cart);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (req.getTimeStart() != null
                && req.getTimeEnd() != null
                && !req.getTimeEnd().isEmpty()
                && !req.getTimeStart().isEmpty()) {
            LocalDate startDate = LocalDate.parse(req.getTimeStart(), dateTimeFormatter);
            LocalDate endDate = LocalDate.parse(req.getTimeEnd(), dateTimeFormatter);
            cart.setTimeStart(startDate);
            cart.setTimeEnd(endDate);
            if (req.getListDateSession().size() != 0) {
                dateSessionRepository.deleteAllByCartId(cart.getId());
                historyWorkingRepository.deleteAllByCartId(cart.getId());
                List<DateSession> dateSessionList = new ArrayList<>();
                for (var dateSession : req.getListDateSession()) {
                    EDateInWeek date = EDateInWeek.valueOf(dateSession.getDate());
                    for (var sessionOfDate : dateSession.getSessionOfDateList()) {
                        ESessionOfDate sessionDate = ESessionOfDate.valueOf(sessionOfDate);
                        DateSession newDateSession = new DateSession();
                        newDateSession.setSessionOfDate(sessionDate);
                        newDateSession.setDateInWeek(date);
                        newDateSession.setCart(cart);
                        dateSessionList.add(newDateSession);
                        dateSessionService.create(newDateSession);
                    }
                }
                cart.setDateSessions(dateSessionList);
                historyWorkingService.createHistoryWorkingForCart(cart);
            }
        }
        if (req.getService() != null && !req.getService().isEmpty()) {
            ServiceGeneral serviceGeneral = serviceGeneralService.findById(req.getService());
            cart.setService(serviceGeneral);
        }
        if (req.getListInfoId() != null && !req.getListInfoId().isEmpty()) {
            cartInfoRepository.deleteAllByCartId(cart.getId());
            List<CartInfo> cartInfoList = new ArrayList<>();
            for (var infoElemId : req.getListInfoId()) {
                AddInfo info = addInfoService.findByIdForEdit(infoElemId);
                if (info != null) { // Make sure the info exists
                    CartInfo cartInfo = new CartInfo();
                    cartInfo.setAddInfo(info);
                    cartInfo.setCart(cart);
                    cartInfoService.create(cartInfo);
                    cartInfoList.add(cartInfo);
                }
            }
            cart.setCartInfos(cartInfoList);
        }
        if (req.getListSkillId() != null && !req.getListSkillId().isEmpty()) {
            cartSkillRepository.deleteAllByCartId(cart.getId());
            List<CartSkill> cartSkillList = new ArrayList<>();
            for (var skillElemId : req.getListSkillId()) {
                Skill skill = skillService.findByIdForEdit(skillElemId);
                if (skill != null) { // Make sure the skill exists
                    CartSkill cartSkill = new CartSkill();
                    cartSkill.setSkill(skill);
                    cartSkill.setCart(cart);
                    cartSkillService.create(cartSkill);
                    cartSkillList.add(cartSkill);
                }
            }
            cart.setCartSkills(cartSkillList);
        }

        if (req.getLatitude() != null && !req.getLatitude().isEmpty()
                && req.getLongitude() != null && !req.getLongitude().isEmpty()
                && req.getNameLocation() != null && !req.getNameLocation().isEmpty()
                && req.getDistanceForWork() != null && !req.getDistanceForWork().isEmpty()) {

            LocationPlace locationPlace = new LocationPlace();
            locationPlace.setName(req.getNameLocation());
            locationPlace.setDistanceForWork(Double.valueOf(req.getDistanceForWork()));
            locationPlace.setLatitude(Double.valueOf(req.getLatitude()));
            locationPlace.setLongitude(Double.valueOf(req.getLongitude()));
            locationPlaceService.create(locationPlace);
            cart.setLocationPlace(locationPlace);

        } else if (((req.getLatitude() == null || req.getLatitude().isEmpty())
                || (req.getLongitude() == null || req.getLongitude().isEmpty())
                || (req.getNameLocation() == null || req.getNameLocation().isEmpty()))
                && (req.getDistanceForWork() != null && !req.getDistanceForWork().isEmpty())) {

            LocationPlace locationPlace = cart.getLocationPlace();
            locationPlace.setDistanceForWork(Double.valueOf(req.getDistanceForWork()));
            locationPlaceService.create(locationPlace);
        }
        cartRepository.save(cart);
        Page<EmployeeFilterResponse> filterList = filter(cart.getId(),pageable);
        return filterList;
    }

    public String createCartSale(String id) {
        Cart cart = new Cart();
        Optional<Saler> saler = salerRepository.findById(id);
        Saler saler1 = saler.get();
        cart.setSaler(saler1);
        cartRepository.save(cart);
        return cart.getId();
    }

    public void deleteById(String id) {
        cartRepository.deleteById(id);
    }

    public void editCartBySale(CartSaleEditRequest request, String id) {
        Cart cart = cartRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Cart", id)));

        cart.setFirstName(request.getFirstName());
        cart.setLastName(request.getLastName());
        cart.setPhone(request.getPhone());
        cart.setSaleNote(request.getSaleNote());
        cart.setTimeStart(LocalDate.parse(request.getTimeStart()));
        cart.setTimeEnd(LocalDate.parse(request.getTimeEnd()));
    }

    public Page<CartAllFieldResponse> findAllCartByUserId(String userId, Pageable pageable) {
        Page<CartAllFieldResponse> listCart = cartRepository.findAllCartByUserId(userId, pageable);

        for (var elem : listCart) {
            List<CartSkillInfoServiceResponse> infoList = new ArrayList<>();
            Cart cart = findById(elem.getId());
            for (var e : cart.getCartInfos()) {
                CartSkillInfoServiceResponse info = new CartSkillInfoServiceResponse();
                info.setId(e.getAddInfo().getId());
                info.setName(e.getAddInfo().getName());
                infoList.add(info);
            }
            elem.setInfoList(infoList);

            List<CartSkillInfoServiceResponse> skillList = new ArrayList<>();
            for (var e : cart.getCartSkills()) {
                CartSkillInfoServiceResponse skill = new CartSkillInfoServiceResponse();
                skill.setId(e.getSkill().getId());
                skill.setName(e.getSkill().getName());
                skillList.add(skill);
            }
            elem.setInfoList(skillList);

            CartSkillInfoServiceResponse service = new CartSkillInfoServiceResponse();
            service.setId(cart.getService().getId());
            service.setName(cart.getService().getName());
            service.setDesciption(cart.getService().getDescription());
            elem.setService(service);

            CartLocationPlaceRepsonse location = new CartLocationPlaceRepsonse();
            location.setName(cart.getLocationPlace().getName());
            location.setDistanceForWork(cart.getLocationPlace().getDistanceForWork());
            location.setLongitude(cart.getLocationPlace().getLongitude());
            location.setLatitude(cart.getLocationPlace().getLatitude());
            elem.setLocationPlace(location);

            CartContactEmployeeResponse contactEmployeeResponse = new CartContactEmployeeResponse();
            ContactEmployee contactEmployee = cart.getContactEmployees();
            if (contactEmployee != null) {
                contactEmployeeResponse.setContactStatus(contactEmployee.getContactStatus());
                contactEmployeeResponse.setIdEmployee(contactEmployee.getEmployee().getId());
                contactEmployeeResponse.setLastName(contactEmployee.getEmployee().getLastName());
                contactEmployeeResponse.setFirstName(contactEmployee.getEmployee().getFirstName());
                contactEmployeeResponse.setFee(contactEmployee.getFee());
                contactEmployeeResponse.setDateTime(contactEmployee.getDateTime());
                elem.setContactEmployee(contactEmployeeResponse);
            }

            List<CartDateSessionResponse> dateSessionResponseList = new ArrayList<>();
            for (var e : dateSessionRepository.findAllByCartId(cart.getId())) {
                CartDateSessionResponse dateSessionResponse = new CartDateSessionResponse();
                dateSessionResponse.setSessionOfDate(e.getSessionOfDate());
                dateSessionResponse.setDateInWeek(e.getDateInWeek());
                dateSessionResponseList.add(dateSessionResponse);
            }
            elem.setDateSessionResponseList(dateSessionResponseList);

            List<CartHistoryWorkingResponse> historyWorkingResponseList = new ArrayList<>();
            for (var e : historyWorkingRepository.findAllByCartId(cart.getId())) {
                CartHistoryWorkingResponse historyWorkingResponse = new CartHistoryWorkingResponse();
                historyWorkingResponse.setSessionOfDate(e.getSessionOfDate());
                historyWorkingResponse.setDateInWeek(e.getDateInWeek());
                historyWorkingResponse.setDateWork(e.getDateWork());
                historyWorkingResponseList.add(historyWorkingResponse);
            }
            elem.setHistoryWorkingResponseList(historyWorkingResponseList);
        }
        return listCart;
    }
}
