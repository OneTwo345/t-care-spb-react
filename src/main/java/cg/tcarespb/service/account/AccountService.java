package cg.tcarespb.service.account;

import cg.tcarespb.repository.AccountRepository;
import cg.tcarespb.service.account.response.AccountListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public List<AccountListResponse> getListAccount(){
        return accountRepository.findAll()
                .stream()
                .map(account -> AccountListResponse.builder()
                        .id(account.getId())
                        .email(account.getEmail())
                        .build())
                .collect(Collectors.toList());
    }
}
