package cg.tcarespb.service.cart;

import cg.tcarespb.models.Cart;
import cg.tcarespb.repository.CartRepository;
import cg.tcarespb.service.cartService.CartServiceService;
import cg.tcarespb.service.cartService.request.CartServiceListSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartServiceService cartServiceService;

    public void create() {
        Cart cart = new Cart();
        cartRepository.save(cart);
    }

    public Cart findById(String id) {
        return cartRepository.findById(id).get();
    }
    public void updateCartService (CartServiceListSaveRequest req){

    }
}
