package cg.tcarespb.service.cart.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartListResponse {
    private String id;
    private String timeStart;
    private String timeEnd;
    private String createAt;
    private String noteForPatient;
    private String noteForEmployee;
    private String memberOfFamily;
    private String gender;
    private String eDecade;
    private String locationPlace;
    private String firstName;
    private String lastName;
    private String saleNote;
    private String phone;
    private String serviceGeneral;
    private String employeeFirstName;
    private String employeeLastName;
}
