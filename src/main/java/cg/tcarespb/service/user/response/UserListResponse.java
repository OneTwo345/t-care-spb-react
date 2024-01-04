package cg.tcarespb.service.user.response;

import cg.tcarespb.models.enums.EGender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserListResponse {
    private String id;
    private String personID;
    private String firstName;
    private String fullName;
    private EGender gender;
}
