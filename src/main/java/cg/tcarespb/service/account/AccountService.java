package cg.tcarespb.service.account;

import cg.tcarespb.models.Account;
import cg.tcarespb.registration.password.PasswordResetTokenService;
import cg.tcarespb.registration.token.VerificationToken;
import cg.tcarespb.registration.token.VerificationTokenRepository;
import cg.tcarespb.repository.AccountRepository;
import cg.tcarespb.service.account.response.AccountListResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordResetTokenService passwordResetTokenService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;



    public List<AccountListResponse> getListAccount() {
        return accountRepository.findAll()
                .stream()
                .map(account -> AccountListResponse.builder()
                        .id(account.getId())
                        .email(account.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    public void createPasswordResetTokenForUser(Account account, String passwordResetToken) {
        passwordResetTokenService.createPasswordResetTokenForAccount(account, passwordResetToken);
    }

    public String validateToken(String theToken) {
        VerificationToken token = verificationTokenRepository.findByToken(theToken);
        if (token == null) {
            return "Invalid verification token";
        }
        Account account = token.getAccount();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            return "Verification link already expired," +
                    " Please, click the link below to receive a new verification link";
        }
        accountRepository.save(account);
        return "valid";
    }

    public void changePassword(Account theAccount, String newPassword) {
        theAccount.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(theAccount);
    }

    public Account findAccountByPasswordToken(String token) {
        return passwordResetTokenService.findUserByPasswordToken(token).get();
    }
    public boolean oldPasswordIsValid(Account account, String oldPassword){
        return passwordEncoder.matches(oldPassword, account.getPassword());
    }
}
