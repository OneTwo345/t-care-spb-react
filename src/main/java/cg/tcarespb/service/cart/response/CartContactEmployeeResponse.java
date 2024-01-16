package cg.tcarespb.service.cart.response;

import cg.tcarespb.models.enums.EContactStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartContactEmployeeResponse {
    private BigDecimal fee;
    private LocalDateTime dateTime;
    private EContactStatus contactStatus;
    private String idEmployee;
    private String firstName;
    private String lastName;
}
