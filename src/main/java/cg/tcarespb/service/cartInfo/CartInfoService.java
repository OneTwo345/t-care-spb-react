package cg.tcarespb.service.cartInfo;

import cg.tcarespb.models.CartInfo;
import cg.tcarespb.repository.CartInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartInfoService {
    private final CartInfoRepository cartInfoRepository;
    public CartInfo create (CartInfo cartInfo){
        return cartInfoRepository.save(cartInfo);
    }
}
