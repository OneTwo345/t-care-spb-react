package cg.tcarespb.service.account.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginGoogleSaveRequest {
    private String name;
    private String email;
    private String picture;
}