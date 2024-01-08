package cg.tcarespb.service.account.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountSaveRequest {
    private String lastName;
    private String firstName;
    private String password;
    private String role;
    private String personId;
    private String email;
    private String gender;
}
