package cg.tcarespb.service.cart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartLocationSaveRequest {
    private String nameLocation;
    private String distanceForWork;
    private String longitude;
    private String latitude;
}
