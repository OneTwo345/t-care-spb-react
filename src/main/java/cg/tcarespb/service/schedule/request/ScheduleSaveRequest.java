package cg.tcarespb.service.schedule.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ScheduleSaveRequest {
    private String id;
    private String hourPerWeekMin;
    private String hourPerWeekMax;
    private String priceMin;
    private String priceMax;
    private String minHourPerJob;
    private String jobType;
    private String employeeId;
}
