package cg.tcarespb.repository;

import cg.tcarespb.models.CartInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartInfoRepository extends JpaRepository<CartInfo,String> {
}
