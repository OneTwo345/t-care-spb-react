package cg.tcarespb.service.contract.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractLocationResponse {
    private String name;
    private Double distanceForWork;
    private Double longitude;
    private Double latitude;
}
