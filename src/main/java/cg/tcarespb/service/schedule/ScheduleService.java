package cg.tcarespb.service.schedule;

import cg.tcarespb.models.Employee;
import cg.tcarespb.models.Schedule;
import cg.tcarespb.repository.EmployeeRepository;
import cg.tcarespb.repository.ScheduleRepository;
import cg.tcarespb.service.schedule.request.ScheduleSaveRequest;
import cg.tcarespb.service.schedule.response.ScheduleListResponse;
import cg.tcarespb.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                        .employeeId(service.getEmployee().getFirstName())
                        .build())
                        .collect(Collectors.toList());
    }

    public void create(ScheduleSaveRequest request){
        var schedule = AppUtil.mapper.map(request, Schedule.class);
        Optional<Employee> employee = employeeRepository.findById(request.getEmployeeId());
               schedule.setEmployee(employee.get());
        schedule = scheduleRepository.save(schedule);
    }



}
