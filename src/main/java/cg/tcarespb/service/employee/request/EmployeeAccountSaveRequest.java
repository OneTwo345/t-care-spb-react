package cg.tcarespb.service.employee.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeAccountSaveRequest {
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;
    private String password;
    private String email;
}
