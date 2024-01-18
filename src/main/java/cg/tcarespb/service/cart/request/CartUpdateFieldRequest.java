package cg.tcarespb.service.cart.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartUpdateFieldRequest {
    private String firstName;
    private String lastName;
    private String memberOfFamily;
    private String noteForEmployee;
    private String noteForPatient;
    private String phone;
    private String decade;
    private String gender;
}
