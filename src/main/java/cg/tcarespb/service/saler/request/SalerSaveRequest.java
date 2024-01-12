package cg.tcarespb.service.saler.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SalerSaveRequest {
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;
}
