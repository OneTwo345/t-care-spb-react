package cg.tcarespb.service.contract.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ContractSaveRequest {
    private String timeStart;
    private String timeEnd;
    private String namePatient;
    private String agePatient;
    private String content;
    private String pricePerHour;
    private String totalPrice;
    private String dateQuantity;
    private String hourPerDay;
    private String employeeId;
}
