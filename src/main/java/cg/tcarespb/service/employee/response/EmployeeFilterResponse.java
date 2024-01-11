package cg.tcarespb.service.employee.response;

import cg.tcarespb.models.enums.EExperience;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class  EmployeeFilterResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String bio;
    private String descriptionAboutMySelf;
    private EExperience experience;
    private Double longitude;
    private Double latitude;
    private String nameLocation;
    private Double distanceToWork;
    private String address;
    private Float starAverage;
    private Integer rateQuantity;
    private String photoUrl;
    private List<String> skillName;
    private List<String> serviceName;
    private List<String> infoName;

    public EmployeeFilterResponse(String id,String nameLocation, String firstName, String lastName,String bio, String descriptionAboutMySelf, EExperience experience, Double longitude, Double latitude, String address) {
        this.id = id;
        this.nameLocation = nameLocation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio =bio;
        this.descriptionAboutMySelf = descriptionAboutMySelf;
        this.experience = experience;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

}
