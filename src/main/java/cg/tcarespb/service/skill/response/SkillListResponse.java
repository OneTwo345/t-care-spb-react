package cg.tcarespb.service.skill.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SkillListResponse {
    private String id;
    private String name;
}
