package cg.tcarespb.service.addInfo.response;

import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddInfoListResponse {
    private String id;
    private String name;
}
