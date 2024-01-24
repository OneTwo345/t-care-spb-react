package cg.tcarespb.service.contract.response;

import cg.tcarespb.models.enums.EGender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractResponse {
    private String id;
    private LocalDate timeStart;
    private LocalDate timeEnd;
    private LocalDate createAt;
    private String namePatient;
    private String agePatient;
    private String content;
    private String noteForPatient;
    private String noteForEmployee;
    private String nameService;
    private String customerName;
    private String customerPhone;
    private BigDecimal feeContact;
    private BigDecimal feePrice;
    private BigDecimal totalPrice;
    private BigDecimal priceService;
    private BigDecimal totalAmount;
    private BigDecimal amount;
    private BigDecimal feeAmount;
    private EGender gender;
    private String genders;
    private String descriptionService;


    public ContractResponse(String id, LocalDate timeStart, LocalDate timeEnd, LocalDate createAt, String namePatient, String content, String noteForPatient, String noteForEmployee, String nameService, BigDecimal feeContact, BigDecimal feePrice, BigDecimal totalPrice, BigDecimal priceService, BigDecimal totalAmount, BigDecimal amount, BigDecimal feeAmount, EGender gender) {
        this.id = id;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.createAt = createAt;
        this.namePatient = namePatient;
        this.content = content;
        this.noteForPatient = noteForPatient;
        this.noteForEmployee = noteForEmployee;
        this.nameService = nameService;
        this.feeContact = feeContact;
        this.feePrice = feePrice;
        this.totalPrice = totalPrice;
        this.priceService = priceService;
        this.totalAmount = totalAmount;
        this.amount = amount;
        this.feeAmount = feeAmount;
        this.gender = gender;
    }
    private ContractEmployeeUserResponse user;
    private ContractEmployeeUserResponse employee;
    private List<ContractHistoryWorkingResponse> historyWorking;
    private ContractLocationResponse location;
}
