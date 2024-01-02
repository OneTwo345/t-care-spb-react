package cg.tcarespb.service.employee.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeBioSaveRequest {
    private String descriptionAboutMySelf;
    private String bioTitle;
}
