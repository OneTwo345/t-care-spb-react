package cg.tcarespb.repository;

import cg.tcarespb.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart,String> {
}
