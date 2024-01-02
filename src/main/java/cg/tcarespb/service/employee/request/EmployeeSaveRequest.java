package cg.tcarespb.service.employee.request;

import cg.tcarespb.models.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    private List<String> idServices;
    private String gender;
    private String status;
    private String experience;
    private String education;
    private String hourPerWeekMin;
    private String hourPerWeekMax;
    private String priceMin;
    private String priceMax;
    private String minHourPerJob;
    private String jobType;
    private List<EmployeeDateSessionSaveRequest> employeeDateSessionSaveRequests;

}
