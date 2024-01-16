package cg.tcarespb.service.cart.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartLocationPlaceRepsonse {
    private String name;
    private Double distanceForWork;
    private Double longitude;
    private Double latitude;
}
