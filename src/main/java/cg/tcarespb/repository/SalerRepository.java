package cg.tcarespb.repository;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.Saler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalerRepository extends JpaRepository<Saler,String> {
}
