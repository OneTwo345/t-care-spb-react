package cg.tcarespb.service.admin.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminSaveSalerRequest {
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;
    private String email;
    private String password;
    private String phoneNumber;
}
