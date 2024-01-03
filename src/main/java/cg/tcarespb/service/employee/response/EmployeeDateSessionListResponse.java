package cg.tcarespb.service.employee.response;


import cg.tcarespb.service.employee.request.EmployeeDateSessionSaveRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDateSessionListResponse {
    List<EmployeeDateSessionSaveRequest> listDateSession;
}
