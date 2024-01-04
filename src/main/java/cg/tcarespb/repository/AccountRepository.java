package cg.tcarespb.repository;

import cg.tcarespb.models.Account;
import cg.tcarespb.models.CartInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
