package cg.tcarespb.service.employee.request;

import cg.tcarespb.models.enums.EEducation;
import cg.tcarespb.models.enums.EExperience;
import cg.tcarespb.models.enums.EGender;
import cg.tcarespb.models.enums.EStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeSaveRequest {
    private String address;
    private String firstName;
    private String lastName;
    private String descriptionAboutMySelf;
    private String bioTitle;
    private String personID;
    private List<String> idSkills;
    private List<String> idAddInfos;
    private String gender;
    private String status;
    private String experience;
    private String education;

}
