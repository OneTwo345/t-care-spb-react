package cg.tcarespb.service.rate.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RateListResponse {
    private String id;
    private Float starQuantity;
    private String content;
    private Integer rateQuantity;
    private String employeeName;
}
