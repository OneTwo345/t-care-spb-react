package cg.tcarespb.service.account.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountSaveRequest {
    private String id;
    private String email;
    private String password;
}
