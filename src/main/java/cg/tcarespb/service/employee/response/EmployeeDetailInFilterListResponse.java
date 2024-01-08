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
public class EmployeeDetailInFilterListResponse {
    private String id;
    private String firstName;
    private String lastName;
    private EExperience experience;
    private String address;
    private Double distanceToWork;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private List<String> skillName;
    private List<String> serviceName;
    private List<String> infoName;
    private String descriptionAboutMySelf;

}
