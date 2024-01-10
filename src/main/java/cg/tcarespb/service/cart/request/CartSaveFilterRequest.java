package cg.tcarespb.service.cart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartSaveFilterRequest {
    private String timeStart;
    private String timeEnd;
    private List<String> listSkillId;
    private List<String> listInfoId;
    private String service;
    private String longitude;
    private String latitude;
    private String distanceForWork;
    private String nameLocation;
    List<CartDateSessionSaveRequest> listDateSession;
}
