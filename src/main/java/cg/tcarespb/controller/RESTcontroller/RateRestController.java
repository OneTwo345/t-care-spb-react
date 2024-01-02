package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.service.rate.RateService;
import cg.tcarespb.service.rate.request.RateSaveRequest;
import cg.tcarespb.service.rate.response.RateListResponse;
import cg.tcarespb.service.skill.SkillService;
import cg.tcarespb.service.skill.request.SkillSaveRequest;
import cg.tcarespb.service.skill.response.SkillListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
@AllArgsConstructor
public class RateRestController {
    private final RateService rateService;

    @GetMapping()
    public ResponseEntity<List<RateListResponse>> getRateListResponse() {
        List<RateListResponse> rateListResponses = rateService.getRateListResponse();
        return ResponseEntity.ok(rateListResponses);
    }

    @PostMapping
    public void create(@RequestBody RateSaveRequest request){
        rateService.create(request);
    }
}
