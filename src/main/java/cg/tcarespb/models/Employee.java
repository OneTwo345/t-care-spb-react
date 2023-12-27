package cg.tcarespb.models;


import cg.tcarespb.models.enums.EEducation;
import cg.tcarespb.models.enums.EExperience;
import cg.tcarespb.models.enums.EGender;
import cg.tcarespb.models.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table( name = "employees")
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String address;
    private String firstName;
    private String lastName;
    private String descriptionAboutMySelf;
    private String bioTitle;
    private String experience;
    private String personID;
    private Boolean deleted = false;

    @Enumerated(EnumType.STRING)
    private EGender gender;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    @Enumerated(EnumType.STRING)
    private EExperience eExperience;
    @Enumerated(EnumType.STRING)
    private EEducation education;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeInfo> employeeInfos;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeService> employeeServices;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeSkill> employeeSkills;

    @OneToMany(mappedBy = "employee")
    private List<Availability> availabilities;

    @OneToMany(mappedBy = "employee")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "employee")
    private List<FeeContactEmployee> feeContactEmployees;

    @OneToMany(mappedBy = "employee")
    private List<Rate> rates;
}
