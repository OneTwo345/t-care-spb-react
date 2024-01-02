package cg.tcarespb.service.cartService;

import cg.tcarespb.repository.CartServiceGeneralRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import cg.tcarespb.models.CartServiceGeneral;

@AllArgsConstructor
@Service
public class CartServiceService {
    private final CartServiceGeneralRepository cartServiceRepository;

    public void create(CartServiceGeneral cartService) {
        cartServiceRepository.save(cartService);
    }
}
