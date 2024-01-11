package cg.tcarespb.service.historyWorking.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HistoryWorkingResponse {
    private String dateOfWeek;
    private String dateSession;
    private String date;
}
