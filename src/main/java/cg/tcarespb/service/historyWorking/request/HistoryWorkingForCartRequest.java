package cg.tcarespb.service.historyWorking.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HistoryWorkingForCartRequest {
    private String timeStart;
    private String timeEnd;
    private String idCart;
}
