package cg.tcarespb.service.cart.response;

import cg.tcarespb.models.enums.EDecade;
import cg.tcarespb.models.enums.EGender;
import cg.tcarespb.models.enums.EMemberOfFamily;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartAllFieldResponse {
    private String id;
    private LocalDate timeStart;
    private LocalDate timeEnd;
    private String noteForPatient;
    private String noteForEmployee;
    private String firstName;
    private String lastName;
    private String saleNote;
    private String phone;
    private EMemberOfFamily memberOfFamily;
    private EGender gender;
    private EDecade decade;
    private BigDecimal totalAmount;;
    private BigDecimal amount;
    private BigDecimal feeAmount;
    private String cartStatus;
    private List<CartSkillInfoServiceResponse> infoList;
    private List<CartSkillInfoServiceResponse> skillList;
    private CartSkillInfoServiceResponse service;
    private CartLocationPlaceRepsonse locationPlace;
    private List<CartDateSessionResponse> dateSessionResponseList;
    private List<CartHistoryWorkingResponse> historyWorkingResponseList;
    private CartEmployeeResponse employee;
    private CartUserResponse user;


    public CartAllFieldResponse(String id,
                                LocalDate timeStart,
                                LocalDate timeEnd,
                                String noteForPatient,
                                String noteForEmployee,
                                String firstName,
                                String lastName,
                                String saleNote,
                                String phone,
                                EMemberOfFamily memberOfFamily,
                                EGender gender,
                                EDecade decade) {
        this.id = id;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.noteForPatient = noteForPatient;
        this.noteForEmployee = noteForEmployee;
        this.firstName = firstName;
        this.lastName = lastName;
        this.saleNote = saleNote;
        this.phone = phone;
        this.memberOfFamily = memberOfFamily;
        this.gender = gender;
        this.decade = decade;
    }
}
