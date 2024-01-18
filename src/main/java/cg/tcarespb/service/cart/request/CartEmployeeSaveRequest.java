package cg.tcarespb.service.cart.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartEmployeeSaveRequest {
    private String cartId;
    private String employeeId;
}
