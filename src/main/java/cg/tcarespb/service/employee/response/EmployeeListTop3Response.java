package cg.tcarespb.service.employee.response;

import cg.tcarespb.models.enums.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class EmployeeListTop3Response {
    private String employeeId;
    private String employeeName;
    private String employeeLocation;
    private String employeeStar;
    private String employeeRateQuantity;
    private String photoURL;
    private String userName;
    private String content;


}
