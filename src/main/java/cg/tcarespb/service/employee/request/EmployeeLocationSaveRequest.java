package cg.tcarespb.service.employee.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeLocationSaveRequest {
    private String nameLocation;
    private String distanceForWork;
    private String longitude;
    private String latitude;
}
