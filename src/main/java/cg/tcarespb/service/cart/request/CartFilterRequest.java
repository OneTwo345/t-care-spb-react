package cg.tcarespb.service.cart.request;

import cg.tcarespb.models.enums.EJobType;
import cg.tcarespb.models.enums.EStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CartFilterRequest {
    private String cartServiceIdList;
    private String cartInfoIdList;
    private String cartSkillIdList;
    private Double distance;
    private Double longitude;
    private Double latitude;
    private BigDecimal priceMax;
    private BigDecimal priceMin;
    private EJobType jobType;
    private EStatus status;


}
