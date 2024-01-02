package cg.tcarespb.service.employee.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDateSessionSaveRequest {
    private String date;
    private List<String> sessionOfDateList;
}
