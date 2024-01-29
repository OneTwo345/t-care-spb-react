package cg.tcarespb.service.cart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartSaveRequest {
    private String timeStart;
    private String timeEnd;
    private String noteForPatient;
    private String noteForEmployee;
    private String memberOfFamily;
    private String gender;
    private String eDecade;
    private String locationPlace;
    private String distanceForWork;
    private String longitude;   
    private String latitude;
    private String firstName;
    private String lastName;
    private String saleNote;
    private String phone;
    private String serviceId;
    List<CartDateSessionSaveRequest> listDateSession;
    private List<String> idSkills;
    private List<String> idAddInfos;
}
