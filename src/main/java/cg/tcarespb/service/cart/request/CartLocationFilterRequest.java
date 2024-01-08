package cg.tcarespb.service.cart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartLocationFilterRequest {
    private Double distance;
    private Double longitude;
    private Double latitude;
}
