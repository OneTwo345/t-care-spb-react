package cg.tcarespb.service.admin.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminStartEndDayRequest {
    private String startDay;
    private String endDay;
}
