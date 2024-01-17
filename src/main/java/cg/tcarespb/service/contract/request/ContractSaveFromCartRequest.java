package cg.tcarespb.service.contract.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContractSaveFromCartRequest {
    private String cartId;
    private String employeeId;
}
