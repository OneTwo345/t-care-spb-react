package cg.tcarespb.registration.password;

import cg.tcarespb.models.Account;
import cg.tcarespb.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public void createPasswordResetTokenForAccount(Account account, String passwordToken) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(passwordToken, account);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    public String validatePasswordResetToken(String passwordResetToken) {
        PasswordResetToken passwordToken = passwordResetTokenRepository.findByToken(passwordResetToken);
        if (passwordToken == null) {
            return "Invalid verification token";
        }
        Account account = passwordToken.getAccount();
        Calendar calendar = Calendar.getInstance();
        if ((passwordToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            return "Link already expired, resend link";
        }
        return "valid";
    }

    public Optional<Account> findUserByPasswordToken(String passwordResetToken) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(passwordResetToken).getAccount());
    }

    public PasswordResetToken findPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }
}
