package cg.tcarespb.service.employee.response;

import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.models.enums.ESessionOfDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeHistoryWorkingResponse {
    private EDateInWeek dateInWeek;
    private ESessionOfDate sessionOfDate;
    private LocalDate dateWork;

}
