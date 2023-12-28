package cg.tcarespb.service.cart;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.DateSession;
import cg.tcarespb.models.ServiceGeneral;
import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.models.enums.ESessionOfDate;
import cg.tcarespb.repository.CartRepository;
import cg.tcarespb.service.cart.request.CartDateSessionListSaveRequest;
import cg.tcarespb.service.cartService.CartServiceService;
import cg.tcarespb.service.cart.request.CartServiceListSaveRequest;
import cg.tcarespb.service.dateSession.DateSessionService;
import cg.tcarespb.service.serviceGeneral.ServiceGeneralService;
import cg.tcarespb.util.AppMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<Cart> getAllByIdUser(String userId){
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
    public void updateDateSessionCart(CartDateSessionListSaveRequest req, String idCart) {
        Cart cart = findById(idCart);
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
}
