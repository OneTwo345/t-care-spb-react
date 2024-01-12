package cg.tcarespb.controller.RESTcontroller.authentication.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AuthResponse {
    private String jwt;
    private Boolean isAdmin;
    private String idUser;


}
