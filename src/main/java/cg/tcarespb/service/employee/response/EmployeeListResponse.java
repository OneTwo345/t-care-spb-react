package cg.tcarespb.service.employee.response;

import cg.tcarespb.models.Schedule;
import cg.tcarespb.models.enums.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
    private List<String> dateSessions;
    private List<String> services;

    private Integer hourPerWeekMin;
    private Integer hourPerWeekMax;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private Integer minHourPerJob;
    private EJobType jobType;



}
