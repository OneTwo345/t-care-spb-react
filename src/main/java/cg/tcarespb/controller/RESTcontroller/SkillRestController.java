package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.service.addInfo.request.AddInfoEditRequest;
import cg.tcarespb.service.addInfo.request.AddInfoSaveRequest;
import cg.tcarespb.service.addInfo.response.AddInfoDetailResponse;
import cg.tcarespb.service.addInfo.response.AddInfoListResponse;
import cg.tcarespb.service.skill.SkillService;
import cg.tcarespb.service.skill.request.SkillEditRequest;
import cg.tcarespb.service.skill.request.SkillSaveRequest;
import cg.tcarespb.service.skill.response.SkillDetailResponse;
import cg.tcarespb.service.skill.response.SkillListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@AllArgsConstructor
public class SkillRestController {
    private SkillService skillService;

    @GetMapping()
    public ResponseEntity<List<SkillListResponse>> getSkillListResponse() {
        List<SkillListResponse> skillList = skillService.getSkillList();
        return ResponseEntity.ok(skillList);
    }

    @PostMapping
    public void create(@RequestBody SkillSaveRequest request){
        skillService.create(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDetailResponse> findById(@PathVariable("id") String id){
        SkillDetailResponse skill = skillService.findById(id);
        return ResponseEntity.ok(skill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> edit(@PathVariable("id") String id, @RequestBody SkillEditRequest request) {
        skillService.edit(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        skillService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
