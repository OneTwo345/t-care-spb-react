package cg.tcarespb.service.cart;

import cg.tcarespb.models.*;
import cg.tcarespb.models.enums.*;
import cg.tcarespb.repository.CartRepository;
import cg.tcarespb.repository.EmployeeRepository;
import cg.tcarespb.repository.LocationPalaceRepository;
import cg.tcarespb.service.addInfo.AddInfoService;
import cg.tcarespb.service.cart.request.*;
import cg.tcarespb.service.cartInfo.CartInfoService;
import cg.tcarespb.service.cartSkill.CartSkillService;
import cg.tcarespb.service.dateSession.DateSessionService;
import cg.tcarespb.service.employee.response.EmployeeFilterResponse;
import cg.tcarespb.service.location.LocationPalaceService;
import cg.tcarespb.service.serviceGeneral.ServiceGeneralService;
import cg.tcarespb.service.skill.SkillService;
import cg.tcarespb.util.AppMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ServiceGeneralService serviceGeneralService;
    private final DateSessionService dateSessionService;
    private final LocationPalaceService locationPalaceService;
    private final CartSkillService cartSkillService;
    private final SkillService skillService;
    private final CartInfoService cartInfoService;
    private final AddInfoService addInfoService;
    private final EmployeeRepository employeeRepository;
    private final LocationPalaceRepository locationPalaceRepository;

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

    public void updateCartService(CartServiceListSaveRequest req, String cartId) {
        Cart cart = findById(cartId);
        ServiceGeneral serviceGeneral = serviceGeneralService.findById(req.getServiceId());
        cart.setService(serviceGeneral);
        cartRepository.save(cart);
    }

    public void updateDateSessionCart(CartDateSessionListSaveRequest req, String cartId) {
        Cart cart = findById(cartId);
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
        cartRepository.save(cart);
    }





    public void updateInfoPatient(CartInfoPatientSaveRequest req, String cardId) {
        Cart cart = findById(cardId);
        cart.setNamePatient(req.getNamePatient());
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
        LocationPlace locationPalace = new LocationPlace();
        locationPalace.setName(req.getNameLocation());
        locationPalace.setDistanceForWork(Double.valueOf(req.getDistanceForWork()));
        locationPalace.setLatitude(Double.valueOf(req.getLatitude()));
        locationPalace.setLongitude(Double.valueOf(req.getLongitude()));
        locationPalaceService.create(locationPalace);
        cart.setLocationPlace(locationPalace);
        cartRepository.save(cart);
    }

    public void updateCartSkill(CartSkillSaveRequest req, String cartId) {
        Cart cart = findById(cartId);
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

    public void createCartForFilter(CartSaveFilterRequest req){
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
        locationPalace.setCart(cart);
        locationPalaceService.create(locationPalace);
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

        cartRepository.save(cart);

    }


//    public Page<String> filter(String idCart, Pageable pageable) {
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
//        Page<String> employeeList = employeeRepository.filter(request, pageable);
//        return employeeList;
//    }

//    public List<String> filterTest(String idCart) {
//        Cart cart = findById(idCart);
//        CartSkillFilterRequest req = new CartSkillFilterRequest();
//        req.setCartSkillIdList(cart.getCartSkills().stream().map(e -> e.getSkill().getId()).collect(Collectors.joining(",")));
//        CartFilterRequest request = new CartFilterRequest();
//        request.setCartServiceId(cart.getServiceGenerals().getId());
//        request.setCartSkillIdList(cart.getCartSkills().stream().map(e -> e.getSkill().getId()).collect(Collectors.joining(",")));
//        request.setCartInfoIdList(cart.getCartInfos().stream().map(e -> e.getAddInfo().getId()).collect(Collectors.joining(",")));
//        request.setDistance(cart.getLocationPlace().getDistanceForWork());
//        request.setLatitude(cart.getLocationPlace().getLatitude());
//        request.setLongitude(cart.getLocationPlace().getLongitude());
//        request.setStatus(EStatus.ACTIVE);
//        List<String> employeeList = employeeRepository.filterTest(request);
//        return employeeList;
//    }

}
