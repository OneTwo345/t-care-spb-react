package cg.tcarespb.service.saler.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SalerListResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;
}
