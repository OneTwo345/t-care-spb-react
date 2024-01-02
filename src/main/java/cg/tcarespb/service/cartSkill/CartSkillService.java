package cg.tcarespb.service.cartSkill;

import cg.tcarespb.models.CartSkill;
import cg.tcarespb.repository.CartSkillRepository;
import cg.tcarespb.util.AppMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartSkillService {
    private final CartSkillRepository cartSkillRepository;

    public CartSkill create(CartSkill cartSkill) {
        return cartSkillRepository.save(cartSkill);
    }
    public CartSkill findById(String cartSkillId){
        return cartSkillRepository.findById(cartSkillId).orElseThrow(()->new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "CartSkill", cartSkillId)));
    }
}
