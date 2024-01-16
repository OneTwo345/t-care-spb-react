package cg.tcarespb.service.cart.response;

import cg.tcarespb.models.enums.EDecade;
import cg.tcarespb.models.enums.EGender;
import cg.tcarespb.models.enums.EMemberOfFamily;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Integer agePatient;
    private String noteForPatient;
    private String noteForEmployee;
    private String firstName;
    private String lastName;
    private String saleNote;
    private String phone;
    private EMemberOfFamily memberOfFamily;
    private EGender gender;
    private EDecade decade;
    private List<CartSkillInfoServiceResponse> infoList;
    private List<CartSkillInfoServiceResponse> skillList;
    private CartSkillInfoServiceResponse service;
    private CartLocationPlaceRepsonse locationPlace;
    private CartContactEmployeeResponse contactEmployee;
    private List<CartDateSessionResponse> dateSessionResponseList;
    private List<CartHistoryWorkingResponse> historyWorkingResponseList;

}
