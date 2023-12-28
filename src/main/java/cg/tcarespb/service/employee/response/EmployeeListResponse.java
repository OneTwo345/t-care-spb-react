package cg.tcarespb.service.employee.response;

import cg.tcarespb.models.enums.EEducation;
import cg.tcarespb.models.enums.EExperience;
import cg.tcarespb.models.enums.EGender;
import cg.tcarespb.models.enums.EStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EmployeeListResponse {
    private String id;
    private String address;
    private String firstName;
    private String lastName;
    private String descriptionAboutMySelf;
    private String bioTitle;
    private String personID;
    private EGender gender;
    private EStatus status;
    private EExperience experience;
    private EEducation education;
    private List<String> skills;
    private List<String> addInfos;


}
