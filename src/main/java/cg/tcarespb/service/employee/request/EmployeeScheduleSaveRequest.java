package cg.tcarespb.service.employee.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeScheduleSaveRequest {
    private String hourPerWeekMin;
    private String hourPerWeekMax;
    private String priceMin;
    private String priceMax;
    private String minHourPerJob;
    private String jobType;
}
