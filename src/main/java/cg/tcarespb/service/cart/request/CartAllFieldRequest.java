package cg.tcarespb.service.cart.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CartAllFieldRequest {
    private String longitude;
    private String latitude;
    private String distanceForWork;
    private String nameLocation;
    private List<String> listSkillId;
    private List<String> listInfoId;
    private String service;
    private String timeStart;
    private String timeEnd;
    List<CartDateSessionSaveRequest> listDateSession;
}
