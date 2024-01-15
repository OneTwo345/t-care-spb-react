package cg.tcarespb.service.contactEmployee.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContactEmployeeDateSaveRequest {
    private String idContactEmployee;
    private String date;
}
