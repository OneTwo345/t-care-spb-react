package cg.tcarespb.service.serviceGeneral.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor

public class ServiceSaveRequest {
    private String name ;
    private String description;
    private BigDecimal priceEmployee;
    private BigDecimal fees;
    private BigDecimal totalPrice;
}
