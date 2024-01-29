package cg.tcarespb.models;


import cg.tcarespb.models.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "employees")
@SQLDelete(sql = "UPDATE employees SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String firstName;
    private String lastName;
    @Column(length = 2000)
    @Length(max = 2000, message = "Description must not exceed 2000 characters")
    private String descriptionAboutMySelf;
    @Column(length = 2000)
    @Length(max = 2000, message = "Description must not exceed 2000 characters")
    private String bioTitle;
    private String personID;
    private Boolean deleted = false;
    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private EGender gender;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    @Enumerated(EnumType.STRING)
    private EExperience experience;
    @Enumerated(EnumType.STRING)
    private EEducation education;
    @OneToMany(mappedBy = "employee")
    private List<Contract> contracts;
    @OneToMany(mappedBy = "employee")
    private List<EmployeeInfo> employeeInfos;
    @OneToMany(mappedBy = "employee")
    private List<EmployeeServiceGeneral> employeeServiceGenerals;
    @OneToMany(mappedBy = "employee")
    private List<EmployeeSkill> employeeSkills;
    @OneToMany(mappedBy = "employee")
    private List<Favorite> favorites;
    @OneToMany(mappedBy = "employee")
    private List<Rate> rates;
    @OneToMany(mappedBy = "employee")
    private List<DateSession> dateSessions;
    @OneToOne
    private LocationPlace locationPlace;
    @OneToMany(mappedBy = "employee")
    private List<HistoryWorking> historyWorking;
    @OneToOne
    private Photo photo;
    public Employee(String id) {
        this.id = id;
    }
}
