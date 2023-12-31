package cg.tcarespb.service.dateSession.response;

import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.models.enums.ESessionOfDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DateSessionListResponseForEmployee {
    private String id;
    private EDateInWeek dateInWeek;
    private ESessionOfDate sessionOfDate;
}
