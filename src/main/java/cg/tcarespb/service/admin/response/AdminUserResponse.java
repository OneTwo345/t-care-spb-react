package cg.tcarespb.service.admin.response;

import cg.tcarespb.models.enums.EGender;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminUserResponse {
    private String id;
    private String personID;
    private String firstName;
    private String lastName;
    private EGender gender;
}
