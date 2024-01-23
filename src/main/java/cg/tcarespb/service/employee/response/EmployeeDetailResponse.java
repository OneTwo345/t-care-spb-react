package cg.tcarespb.service.employee.response;

import cg.tcarespb.models.ServiceGeneral;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDetailResponse {
    private String address;
    private String firstName;
    private String email;
    private String phone;
    private String lastName;
    private String descriptionAboutMySelf;
    private String bioTitle;
    private String personID;
    private List<String> idSkills;
    private List<String> idAddInfos;
    private List<EmployeeRenderServiceResponse> idServices;
    private String gender;
    private String status;
    private String experience;
    private String education;
    private String photoUrl;
    private String hourPerWeekMin;
    private String hourPerWeekMax;
    private String priceMin;
    private String priceMax;
    private String minHourPerJob;
    private String time;
    private String jobType;
    private List<String> listDateSessions;
}
