package cg.tcarespb.service.cartService.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartServiceSaveRequest {
    private String idCart;
    private String idGeneralService;
}
