package cg.tcarespb.service.schedule.response;

import cg.tcarespb.models.enums.EJobType;
import cg.tcarespb.models.enums.ESessionOfDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ScheduleListResponse {
    private String id;
    private Integer hourPerWeekMin;
    private Integer hourPerWeekMax;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private Integer minHourPerJob;
    private EJobType jobType;
    private ESessionOfDate sessionOfDate;
    private String employeeId;

}
