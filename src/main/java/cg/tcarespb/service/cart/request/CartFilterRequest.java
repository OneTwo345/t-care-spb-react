package cg.tcarespb.service.cart.request;

import cg.tcarespb.models.enums.EStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartFilterRequest {
    private String cartId;
    private String cartServiceId;
    private String cartInfoIdList;
    private String cartSkillIdList;
    private Double distance;
    private Double longitude;
    private Double latitude;
    private EStatus status;
}
