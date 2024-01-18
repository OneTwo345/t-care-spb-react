package cg.tcarespb.service.cart.response;

import cg.tcarespb.models.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartUserResponse {
    private String id;
    private String personID;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
}
