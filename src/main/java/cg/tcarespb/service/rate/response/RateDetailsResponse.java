package cg.tcarespb.service.rate.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RateDetailsResponse {
    private String starQuantity;
    private String content;
    private String rateQuantity;
    private String employeeId;
}
