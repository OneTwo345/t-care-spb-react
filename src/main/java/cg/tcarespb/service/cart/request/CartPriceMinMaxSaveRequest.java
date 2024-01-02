package cg.tcarespb.service.cart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CartPriceMinMaxSaveRequest {
    private BigDecimal priceMin;
    private BigDecimal priceMax;
}
