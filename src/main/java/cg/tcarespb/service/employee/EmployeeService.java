package cg.tcarespb.service.employee;

import cg.tcarespb.models.*;
import cg.tcarespb.models.enums.EExperience;
import cg.tcarespb.repository.*;
import cg.tcarespb.service.dto.response.SelectOptionResponse;
import cg.tcarespb.service.employee.request.EmployeeSaveRequest;
import cg.tcarespb.service.employee.response.EmployeeListResponse;
import cg.tcarespb.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeAddInfoRepository employeeAddInfoRepository;
    private final DateSessionRepository dateSessionRepository;
    private final ScheduleRepository scheduleRepository;
    private final RateRepository rateRepository;

    public List<EmployeeListResponse> getEmployeeList(){
        return employeeRepository.findAll()
                .stream()
                .map(service -> EmployeeListResponse.builder()
                        .id(service.getId())
                        .address(service.getAddress())
                        .firstName(service.getFirstName())
                        .lastName(service.getLastName())
                        .descriptionAboutMySelf(service.getDescriptionAboutMySelf())
                        .bioTitle(service.getBioTitle())
                        .personID(service.getPersonID())
                        .gender(service.getGender())
                        .status(service.getStatus())
                        .education(service.getEducation())
                        .experience(service.getExperience())
                        .hourPerWeekMin(service.getHourPerWeekMin())
                        .hourPerWeekMax(service.getHourPerWeekMax())
                        .priceMin(service.getPriceMin())
                        .priceMax(service.getPriceMax())
                        .minHourPerJob(service.getMinHourPerJob())
                        .jobType(service.getJobType())

                        .skills(service.getEmployeeSkills()
                                .stream()
                                .map(employeeSkill -> employeeSkill.getSkill().getName())
                                .collect(Collectors.toList()))
                        .addInfos(service.getEmployeeInfos()
                                .stream()
                                .map(employeeInfo -> employeeInfo.getAddInfo().getName())
                                .collect(Collectors.toList())
                        )
                        .dateSessions(service.getDateSessions()
                                .stream()
                                .map(dateSession -> dateSession.getDateInWeek().getName() + " : " + dateSession.getSessionOfDate().getName())
                                .collect(Collectors.toList())
                        )
                        .build())
                .collect(Collectors.toList());
    }

    public void create(EmployeeSaveRequest request){
        var employee = AppUtil.mapper.map(request, Employee.class);
        employee = employeeRepository.save(employee);

        Employee employeeData = employee;
        employeeSkillRepository.saveAll(request
                .getIdSkills()
                .stream()
                .map(id -> new EmployeeSkill(employeeData, new Skill(String.valueOf(id))))
                .collect(Collectors.toList())
        );
        employeeAddInfoRepository.saveAll(request
                .getIdAddInfos()
                .stream()
                .map(id -> new EmployeeInfo(employeeData, new AddInfo(String.valueOf(id))))
                .collect(Collectors.toList())
        );
//        List<DateSession> dateSessions = request.getIdDateSessions().stream()
//                .map(id -> new DateSession(id, employeeData.getDateSessions()))
//                .collect(Collectors.toList());
//        dateSessionRepository.saveAll(dateSessions);
    }



    public List<SelectOptionResponse> findAll() {
        return employeeRepository.findAll()
                .stream().map(employee -> new SelectOptionResponse(employee.getId()
                        , employee.getFirstName())).collect(Collectors.toList());
    }

    public void delete(String id){
        employeeRepository.deleteById(id);
    }



}
