package cg.tcarespb.repository;

import cg.tcarespb.models.CartService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartServiceRepository  extends JpaRepository<CartService,String> {
}
