package cg.tcarespb.service.admin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminRevenueResponse {
    private BigDecimal revenueContact;
    private BigDecimal revenueContract;
}
