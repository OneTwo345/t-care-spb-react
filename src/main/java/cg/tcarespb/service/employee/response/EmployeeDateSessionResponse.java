package cg.tcarespb.service.employee.response;

import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.models.enums.ESessionOfDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDateSessionResponse {
    private EDateInWeek dateInWeek;
    private String eDateInWeek;
    private ESessionOfDate sessionOfDate;
    private String eSessionOfDate;
}
