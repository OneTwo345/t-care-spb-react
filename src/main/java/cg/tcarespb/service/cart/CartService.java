package cg.tcarespb.service.cart;

import cg.tcarespb.models.*;
import cg.tcarespb.models.enums.*;
import cg.tcarespb.repository.CartRepository;
import cg.tcarespb.repository.EmployeeRepository;
import cg.tcarespb.service.addInfo.AddInfoService;
import cg.tcarespb.service.cart.request.*;
import cg.tcarespb.service.cartInfo.CartInfoService;
import cg.tcarespb.service.cartService.CartServiceService;
import cg.tcarespb.service.cartSkill.CartSkillService;
import cg.tcarespb.service.dateSession.DateSessionService;
import cg.tcarespb.service.location.LocationPalaceService;
import cg.tcarespb.service.serviceGeneral.ServiceGeneralService;
import cg.tcarespb.service.skill.SkillService;
import cg.tcarespb.util.AppMessage;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ServiceGeneralService serviceGeneralService;
    private final CartServiceService cartServiceService;
    private final DateSessionService dateSessionService;
    private final LocationPalaceService locationPalaceService;
    private final CartSkillService cartSkillService;
    private final SkillService skillService;
    private final CartInfoService cartInfoService;
    private final AddInfoService addInfoService;
    private final EmployeeRepository employeeRepository;

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
        for (var cartService : req.getServiceIdList()) {
            ServiceGeneral serviceGeneral = serviceGeneralService.findById(cartService);
            CartServiceGeneral cartServiceCreate = new CartServiceGeneral();
            cartServiceCreate.setService(serviceGeneral);
            cartServiceCreate.setCart(cart);
            cartServiceService.create(cartServiceCreate);
            cart.getCartServices().add(cartServiceCreate);
        }
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

    public void updateJobType(CartJobTypeSaveRequest req, String cartId) {
        Cart cart = findById(cartId);
        EJobType eJobType = EJobType.valueOf(req.getJobType());
        cart.setEJobType(eJobType);
        cartRepository.save(cart);

    }

    public void updatePriceMinMax(CartPriceMinMaxSaveRequest req, String cardId) {
        Cart cart = findById(cardId);
        cart.setPriceMax(req.getPriceMax());
        cart.setPriceMin(req.getPriceMin());
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
        locationPalace.setDistanceForWork(Float.valueOf(req.getDistanceForWork()));
        locationPalace.setLatitude(Float.valueOf(req.getLatitude()));
        locationPalace.setLongitude(Float.valueOf(req.getLongitude()));
        locationPalace.setCart(cart);
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

    public Page<String> filter(String idCart, Pageable pageable) {
        Cart cart = findById(idCart);
//        CartLocationFilterRequest req = new CartLocationFilterRequest();
        CartSkillFilterRequest req = new CartSkillFilterRequest();
        req.setCartSkillIdList(cart.getCartSkills().stream().map(e->e.getSkill().getId()).collect(Collectors.joining(",")));
//        req.setDistance(cart.getLocationPlace().getDistanceForWork());
//        req.setLatitude(cart.getLocationPlace().getLatitude());
//        req.setLongitude(cart.getLocationPlace().getLongitude());
        Page<String> employeeList = employeeRepository.filterSkill(req, pageable);
        return employeeList;
    }
}
