package cg.tcarespb.service.employee.response;

import cg.tcarespb.models.enums.EExperience;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeFilterResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String descriptionAboutMySelf;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private EExperience experience;
    private Double longitude;
    private Double latitude;
    private Double distanceToWork;
    private String address;
    private List<String> skillName;
    private List<String> serviceName;
    private List<String> infoName;

    public EmployeeFilterResponse(String id, String firstName, String lastName, String descriptionAboutMySelf, EExperience experience, Double longitude, Double latitude, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.descriptionAboutMySelf = descriptionAboutMySelf;
        this.experience = experience;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }
}
