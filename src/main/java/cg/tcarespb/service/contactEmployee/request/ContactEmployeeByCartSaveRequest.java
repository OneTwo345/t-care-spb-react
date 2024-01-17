package cg.tcarespb.service.contactEmployee.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactEmployeeByCartSaveRequest {
    private String idCart;
    private String idEmployee;
    private String dateTime;
}
