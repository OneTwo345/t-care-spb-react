package cg.tcarespb.service.cartService;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.ServiceGeneral;
import cg.tcarespb.repository.CartServiceRepository;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.cartService.request.CartServiceSaveRequest;
import cg.tcarespb.service.serviceGeneral.ServiceGeneralService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartServiceService {

    private final CartServiceRepository cartServiceRepository;
    private final CartService cartService;
    private final ServiceGeneralService serviceGeneralService;

    public void create(CartServiceSaveRequest req) {
        Cart cart = cartService.findById(req.getIdCart());
        ServiceGeneral serviceGeneral= serviceGeneralService.findById(req.getIdGeneralService());
        cg.tcarespb.models.CartService cartServiceCreate = new cg.tcarespb.models.CartService();
        cartServiceCreate.setService(serviceGeneral);
        cartServiceCreate.setCart(cart);
        cartServiceRepository.save(cartServiceCreate);
    }


}
