package cg.tcarespb.service.dateSession.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DateSessionSaveRequestForEmployee {
    private String dateInWeek;
    private String sessionOfDate;
    private String employeeId;
}
