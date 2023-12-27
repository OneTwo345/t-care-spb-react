package cg.tcarespb.service.skill;

import cg.tcarespb.models.AddInfo;
import cg.tcarespb.models.Skill;
import cg.tcarespb.repository.SkillRepository;
import cg.tcarespb.service.addInfo.request.AddInfoEditRequest;
import cg.tcarespb.service.addInfo.response.AddInfoDetailResponse;
import cg.tcarespb.service.addInfo.response.AddInfoListResponse;
import cg.tcarespb.service.skill.request.SkillEditRequest;
import cg.tcarespb.service.skill.request.SkillSaveRequest;
import cg.tcarespb.service.skill.response.SkillDetailResponse;
import cg.tcarespb.service.skill.response.SkillListResponse;
import cg.tcarespb.util.AppMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;

    public List<SkillListResponse> getSkillList(){
        return skillRepository.findAll()
                .stream()
                .map(service -> SkillListResponse.builder()
                        .id(service.getId())
                        .name(service.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public void create(SkillSaveRequest request){
        Skill skill = new Skill();
        skill.setName(request.getName());
        skillRepository.save(skill);
    }

    public Skill findByIdForEdit(String id) {
        return skillRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Skill", id)));
    }

    public SkillDetailResponse findById(String id){
        Skill skill = skillRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Skill", id)));
        return new SkillDetailResponse(skill.getName());
    }

    public void delete(String id){
        skillRepository.deleteById(id);
    }

    public void edit(String id, SkillEditRequest request){
      Skill skill = findByIdForEdit(id);
        skill.setName(request.getName());
        skillRepository.save(skill);

    }
}
