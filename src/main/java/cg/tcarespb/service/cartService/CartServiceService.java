package cg.tcarespb.service.cartService;

import cg.tcarespb.repository.CartServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import  cg.tcarespb.models.CartService;

@AllArgsConstructor
@Service
public class CartServiceService {
    private final CartServiceRepository cartServiceRepository;

    public void create(CartService cartService) {
        cartServiceRepository.save(cartService);
    }
}
