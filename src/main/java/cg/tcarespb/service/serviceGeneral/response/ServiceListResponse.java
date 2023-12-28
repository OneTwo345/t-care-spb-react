package cg.tcarespb.service.serviceGeneral.response;

import lombok.*;


@Getter
@Setter
@Builder
public class ServiceListResponse {
    private String id;
    private String name;
    private String description;
}
