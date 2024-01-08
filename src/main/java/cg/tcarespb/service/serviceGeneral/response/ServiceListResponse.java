package cg.tcarespb.service.serviceGeneral.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ServiceListResponse {
    private String id;
    private String name;
    private String description;
}
