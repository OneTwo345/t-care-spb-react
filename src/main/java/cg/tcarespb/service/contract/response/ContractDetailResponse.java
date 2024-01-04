package cg.tcarespb.service.contract.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ContractDetailResponse {
    private LocalDate timeStart;
    private LocalDate timeEnd;
    private String namePatient;
    private Integer agePatient;
    private String content;
    private BigDecimal pricePerHour;
    private BigDecimal totalPrice;
    private Integer dateQuantity;
    private Integer hourPerDay;
    private String employeeName;
}
