package cg.tcarespb.service.cartService;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.ServiceGeneral;
import cg.tcarespb.repository.CartServiceRepository;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.cartService.request.CartServiceListSaveRequest;
import cg.tcarespb.service.serviceGeneral.ServiceGeneralService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartServiceService {

    private final CartServiceRepository cartServiceRepository;
    private final CartService cartService;
    private final ServiceGeneralService serviceGeneralService;

    public void create(CartServiceListSaveRequest req, String cartId) {
        Cart cart = cartService.findById(cartId);
        for (var cartService : req.getServiceList()) {
            ServiceGeneral serviceGeneral = serviceGeneralService.findById(cartService);
            cg.tcarespb.models.CartService cartServiceCreate = new cg.tcarespb.models.CartService();
            cartServiceCreate.setService(serviceGeneral);
            cartServiceCreate.setCart(cart);
            cartServiceRepository.save(cartServiceCreate);
            cart.getCartServices().add(cartServiceCreate);
        }
    }


}
