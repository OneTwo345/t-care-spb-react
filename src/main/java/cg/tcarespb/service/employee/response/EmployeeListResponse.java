package cg.tcarespb.service.employee.response;

import cg.tcarespb.models.enums.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String descriptionAboutMySelf;
    private String bioTitle;
    private String personID;
    private EGender gender;
    private EStatus status;
    private EExperience experience;
    private EEducation education;
    private List<EmployeeSkillServiceInfoResponse> skillList;
    private List<EmployeeSkillServiceInfoResponse> addInfoList;
    private List<EmployeeSkillServiceInfoResponse> serviceList;
    private List<EmployeeDateSessionResponse> dateSessionList;
    private List <EmployeeHistoryWorkingResponse> historyWorkingList;
    private Double longitude;
    private Double latitude;
    private String nameLocation;
    private String photoUrl;

    public EmployeeListResponse(String id,
                                String firstName,
                                String lastName,
                                String descriptionAboutMySelf,
                                String bioTitle,
                                String personID,
                                EGender gender,
                                EStatus status,
                                EExperience experience,
                                EEducation education,
                                Double longitude,
                                Double latitude,
                                String nameLocation,
                                String photoUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.descriptionAboutMySelf = descriptionAboutMySelf;
        this.bioTitle = bioTitle;
        this.personID = personID;
        this.gender = gender;
        this.status = status;
        this.experience = experience;
        this.education = education;
        this.longitude = longitude;
        this.latitude = latitude;
        this.nameLocation = nameLocation;
        this.photoUrl = photoUrl;
    }
}
