package cg.tcarespb.service.historyWorking.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class HistoryWorkingSaveRequest {
    private String timeStart;
    private String timeEnd;
    private String idEmployee;
    private String idContract;
}
