package cg.tcarespb.service.cart.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartLocationFilterRequest {
    private Float distance;
    private Float longitude;
    private Float latitude;
}
