package cg.tcarespb.service.cart;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.DateSession;
import cg.tcarespb.models.ServiceGeneral;
import cg.tcarespb.models.enums.*;
import cg.tcarespb.repository.CartRepository;
import cg.tcarespb.service.cart.request.*;
import cg.tcarespb.service.cartService.CartServiceService;
import cg.tcarespb.service.dateSession.DateSessionService;
import cg.tcarespb.service.serviceGeneral.ServiceGeneralService;
import cg.tcarespb.util.AppMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ServiceGeneralService serviceGeneralService;
    private final CartServiceService cartServiceService;
    private final DateSessionService dateSessionService;

    public Cart create() {
        Cart cart = new Cart();
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
        for (var cartService : req.getServiceList()) {
            ServiceGeneral serviceGeneral = serviceGeneralService.findById(cartService);
            cg.tcarespb.models.CartService cartServiceCreate = new cg.tcarespb.models.CartService();
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
        saveCart(cart);
    }

    public void updateJobType(CartJobTypeSaveRequest req, String cartId) {
        Cart cart = findById(cartId);
        EJobType eJobType = EJobType.valueOf(req.getJobType());
        cart.setEJobType(eJobType);
        cartRepository.save(cart);
    }

    public void updateTimeStartEnd(CartTimeStartEndSaveRequest req, String cartId) {

    }

    public void updatePriceMinMax(CartPriceMinMaxSaveRequest req, String cardId) {
        Cart cart = findById(cardId);
        cart.setPriceMax(req.getPriceMax());
        cart.setPriceMin(req.getPriceMin());
        cartRepository.save(cart);
    }

    public void updateInfoPatient(CartInfoPatientSaveRequest req, String cardId) {
        Cart cart = findById(cardId);
        cart.setNoteForPatient(req.getNoteForPatient());
        cart.setGender(EGender.valueOf(req.getNoteForPatient()));
        cart.setEDecade(EDecade.valueOf(req.getEDecade()));
        cart.setMemberOfFamily(EMemberOfFamily.valueOf(req.getMemberOfFamily()));
        cartRepository.save(cart);
    }
    public void updateNoteForEmployee(CartNoteForEmployeeSaveRequest req, String cardId) {
        Cart cart = findById(cardId);
        cart.setNoteForEmployee(req.getNoteForEmployee());
        cartRepository.save(cart);
    }

}
