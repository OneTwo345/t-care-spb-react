package cg.tcarespb.service.cart.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartEmployeeResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String descriptionAboutMySelf;
    private String bioTitle;
    private String gender;
    private String experience;
    private String education;
    private String photoUrl;
    private String namePlace;
    private Double distanceForWork;
    private Double longitude;
    private Double latitude;
    private String phone;
}
