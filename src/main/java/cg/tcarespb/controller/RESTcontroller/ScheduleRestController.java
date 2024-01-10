package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.service.schedule.ScheduleService;
import cg.tcarespb.service.schedule.request.ScheduleSaveRequest;
import cg.tcarespb.service.schedule.response.ScheduleListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/schedules")
@CrossOrigin("http://localhost:3000")
public class ScheduleRestController {
    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleListResponse>> getScheduleList(){
        List<ScheduleListResponse> scheduleListResponses = scheduleService.getListScheduleResponse();
        return ResponseEntity.ok(scheduleListResponses);
    }

    @PostMapping
    public void create(@RequestBody ScheduleSaveRequest request){
        scheduleService.create(request);
    }

}
