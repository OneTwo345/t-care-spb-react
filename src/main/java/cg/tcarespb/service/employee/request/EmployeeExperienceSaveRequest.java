package cg.tcarespb.service.employee.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeExperienceSaveRequest {
    private List<String> idSkills;
    private List<String> idAddInfos;
    private List<String> idServices;
    private String education;
    private String experience;
}
