package cg.tcarespb.service.schedule.request;

import cg.tcarespb.models.enums.EJobType;
import cg.tcarespb.models.enums.ESessionOfDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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
    private String sessionOfDate;
    private String employeeId;
}
