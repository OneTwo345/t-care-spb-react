package cg.tcarespb.service.schedule;

import cg.tcarespb.models.Employee;
import cg.tcarespb.repository.EmployeeRepository;
import cg.tcarespb.repository.ScheduleRepository;
import cg.tcarespb.service.schedule.response.ScheduleListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;

    public List<ScheduleListResponse> getListScheduleResponse(){
        return scheduleRepository.findAll()
                .stream()
                .map(service -> ScheduleListResponse.builder()
                        .id(service.getId())
                        .hourPerWeekMin(service.getHourPerWeekMin())
                        .hourPerWeekMax(service.getHourPerWeekMax())
                        .priceMin(service.getPriceMin())
                        .priceMax(service.getPriceMax())
                        .minHourPerJob(service.getMinHourPerJob())
                        .jobType(service.getJobType())
                        .build())
                        .collect(Collectors.toList());
    }



}
