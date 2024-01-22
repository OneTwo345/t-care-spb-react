package cg.tcarespb.service.user.response;

import cg.tcarespb.models.enums.EGender;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponse {
    private String id;
    private String personID;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private String phoneNumber;
    private EGender gender;
    private String email;
    private String password;
    private String time;


}
