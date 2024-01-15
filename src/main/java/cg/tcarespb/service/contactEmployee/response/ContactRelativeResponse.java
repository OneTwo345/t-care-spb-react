package cg.tcarespb.service.contactEmployee.response;

import cg.tcarespb.models.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactRelativeResponse {
    private String id;
    private String personId;
    private String firstName;
    private String lastName;
    private EGender gender;
    private String phoneNumber;
}
