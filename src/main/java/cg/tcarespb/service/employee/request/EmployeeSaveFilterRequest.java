package cg.tcarespb.service.employee.request;

import cg.tcarespb.service.cart.request.CartDateSessionSaveRequest;
import cg.tcarespb.service.rate.request.RateSaveForFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeSaveFilterRequest {
    private String longitude;
    private String latitude;
    private String distanceForWork;
    private String nameLocation;
    private List<String> listSkillId;
    private List<String> listServiceId;
    private List<String> listInfoId;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private String status;
    List<CartDateSessionSaveRequest> listDateSession;
    List<RateSaveForFilter> listRate;
}
