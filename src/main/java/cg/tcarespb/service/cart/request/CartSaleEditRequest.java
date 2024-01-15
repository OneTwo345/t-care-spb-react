package cg.tcarespb.service.cart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartSaleEditRequest {
    private String timeStart;
    private String timeEnd;
    private String firstName;
    private String lastName;
    private String saleNote;
    private String phone;
}
