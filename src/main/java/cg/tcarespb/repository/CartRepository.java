package cg.tcarespb.repository;

import cg.tcarespb.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository  extends JpaRepository<Cart,String> {
    List<Cart>findAllByUserId(String userId);
}
