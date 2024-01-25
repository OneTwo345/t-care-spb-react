package cg.tcarespb.repository;

import cg.tcarespb.models.Account;
import cg.tcarespb.registration.password.PasswordResetToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {
    PasswordResetToken findByToken(String token);
    void deleteByToken(String token);

    PasswordResetToken findByAccount_Id(String account_id);
    void deleteByAccount(Account account);
    void deleteByAccount_Id(String account_id);
}
