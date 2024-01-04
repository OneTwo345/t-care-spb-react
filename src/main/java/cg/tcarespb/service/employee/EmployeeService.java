package cg.tcarespb.service.employee;

import cg.tcarespb.models.*;
import cg.tcarespb.models.enums.*;
import cg.tcarespb.repository.*;
import cg.tcarespb.service.cart.request.CartLocationFilterRequest;
import cg.tcarespb.service.dateSession.DateSessionService;
import cg.tcarespb.service.employee.request.*;
import cg.tcarespb.service.employee.response.EmployeeDateSessionListResponse;
import cg.tcarespb.service.employee.response.EmployeeDetailResponse;
import cg.tcarespb.service.employee.response.EmployeeListResponse;
import cg.tcarespb.util.AppMessage;
import cg.tcarespb.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final DateSessionService dateSessionService;
    private final EmployeeServiceGeneralRepository employeeServiceGeneralRepository;

    public List<EmployeeListResponse> getEmployeeList() {
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
                        .services(service.getEmployeeServiceGenerals()
                                .stream()
                                .map(employeeServiceGeneral -> employeeServiceGeneral.getService().getName())
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

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void create(EmployeeSaveRequest request) {
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
        employeeServiceGeneralRepository.saveAll(request
                .getIdServices()
                .stream()
                .map(id -> new EmployeeServiceGeneral(employeeData, new ServiceGeneral(String.valueOf(id))))
                .collect(Collectors.toList())
        );
        List<DateSession> dateSessionList = new ArrayList<>();
        for (var dateSession : request.getEmployeeDateSessionSaveRequests()) {
            EDateInWeek date = EDateInWeek.valueOf(dateSession.getDate());
            for (var sessionOfDate : dateSession.getSessionOfDateList()) {
                ESessionOfDate sessionDate = ESessionOfDate.valueOf(sessionOfDate);
                DateSession newDateSession = new DateSession();
                newDateSession.setSessionOfDate(sessionDate);
                newDateSession.setDateInWeek(date);
                newDateSession.setEmployee(employee);
                dateSessionList.add(newDateSession);
                dateSessionService.create(newDateSession);
            }
        }
        employee.setDateSessions(dateSessionList);
    }

    public void createScheduleEmployee(EmployeeScheduleSaveRequest request) {
        var employee = AppUtil.mapper.map(request, Employee.class);
        employee = employeeRepository.save(employee);
    }

    public void updateDateSessionEmployee(EmployeeDateSessionListResponse req, String employeeId) {
        Employee employee = findById(employeeId);
        List<DateSession> dateSessionList = new ArrayList<>();
        for (var dateSession : req.getListDateSession()) {
            EDateInWeek date = EDateInWeek.valueOf(dateSession.getDate());
            for (var sessionOfDate : dateSession.getSessionOfDateList()) {
                ESessionOfDate sessionDate = ESessionOfDate.valueOf(sessionOfDate);
                DateSession newDateSession = new DateSession();
                newDateSession.setSessionOfDate(sessionDate);
                newDateSession.setDateInWeek(date);
                newDateSession.setEmployee(employee);
                dateSessionList.add(newDateSession);
                dateSessionService.create(newDateSession);
            }
        }
        employee.setDateSessions(dateSessionList);
        saveEmployee(employee);
    }

    public void updateExperienceEmployee(EmployeeExperienceSaveRequest request, String employeeId) {
        Employee employee = findById(employeeId);
        employee.setExperience(EExperience.valueOf(request.getExperience()));
        employee.setEducation(EEducation.valueOf(request.getEducation()));
        employeeSkillRepository.saveAll(request
                .getIdSkills()
                .stream()
                .map(id -> new EmployeeSkill(employee, new Skill(String.valueOf(id))))
                .collect(Collectors.toList())
        );
        employeeAddInfoRepository.saveAll(request
                .getIdAddInfos()
                .stream()
                .map(id -> new EmployeeInfo(employee, new AddInfo(String.valueOf(id))))
                .collect(Collectors.toList())
        );
        employeeServiceGeneralRepository.saveAll(request
                .getIdServices()
                .stream()
                .map(id -> new EmployeeServiceGeneral(employee, new ServiceGeneral(String.valueOf(id))))
                .collect(Collectors.toList())
        );
        employeeRepository.save(employee);

    }

    public void updateBioEmployee(EmployeeBioSaveRequest request, String employeeId) {
        Employee employee = findById(employeeId);
        employee.setBioTitle(request.getBioTitle());
        employee.setDescriptionAboutMySelf(request.getDescriptionAboutMySelf());
        employeeRepository.save(employee);

    }

    public void updateAccountEmployee(EmployeeAccountSaveRequest request, String employeeId) {
        Employee employee = findById(employeeId);
        employee.setAddress(request.getAddress());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setGender(EGender.valueOf(request.getGender()));
        employee.setPersonID(request.getPersonID());
        employee.setStatus(EStatus.valueOf(request.getStatus()));
        employeeRepository.save(employee);
    }

    public EmployeeDetailResponse findDetailEmployeeById(String id) {
        var employee = employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Employee", id)));

        var result = AppUtil.mapper.map(employee, EmployeeDetailResponse.class);
        result.setIdSkills(employee
                .getEmployeeSkills()
                .stream().map(employeeSkill -> employeeSkill.getSkill().getName())
                .collect(Collectors.toList()));
        result.setIdServices(employee
                .getEmployeeServiceGenerals()
                .stream().map(employeeServiceGeneral -> employeeServiceGeneral.getService().getName())
                .collect(Collectors.toList()));
        result.setIdAddInfos(employee
                .getEmployeeInfos()
                .stream().map(employeeInfo -> employeeInfo.getAddInfo().getName())
                .collect(Collectors.toList()));
        result.setListDateSessions(employee
                .getDateSessions()
                .stream()
                .map(dateSession -> dateSession.getDateInWeek().getName() + " : " + dateSession.getSessionOfDate().getName())
                .collect(Collectors.toList()));

        return result;
    }

    public List<Employee> get3Employee() {
        return rateRepository.findTop3EmployeesWithHighestRate();
    }


    public Employee findById(String id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Employee", id)));
    }
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

}
