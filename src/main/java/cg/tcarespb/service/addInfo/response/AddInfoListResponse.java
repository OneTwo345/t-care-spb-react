package cg.tcarespb.service.addInfo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddInfoListResponse {
    private String id;
    private String name;
}
