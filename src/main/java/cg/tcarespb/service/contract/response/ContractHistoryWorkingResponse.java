package cg.tcarespb.service.contract.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractHistoryWorkingResponse {
    private String dateInWeekName;
    private String sessionOfDateName;
    private LocalDate dateWork;
}
