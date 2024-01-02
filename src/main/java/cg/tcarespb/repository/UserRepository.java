package cg.tcarespb.repository;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
