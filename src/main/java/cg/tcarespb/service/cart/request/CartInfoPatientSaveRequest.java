package cg.tcarespb.service.cart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CartInfoPatientSaveRequest {

private String memberOfFamily;
private String gender;
private String decade;
private String noteForPatient;
}
