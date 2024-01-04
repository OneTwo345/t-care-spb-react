package cg.tcarespb.service.contract.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ContractListResponse {
    private String id;
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
