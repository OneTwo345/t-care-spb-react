package cg.tcarespb.service.user.request;

import cg.tcarespb.models.enums.EGender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveRequest {
    private String personID;
    private String firstName;
    private String fullName;
    private EGender gender;
}
