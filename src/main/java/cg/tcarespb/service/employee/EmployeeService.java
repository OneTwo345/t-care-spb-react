package cg.tcarespb.service.employee;

import cg.tcarespb.models.*;
import cg.tcarespb.models.enums.*;
import cg.tcarespb.repository.*;
import cg.tcarespb.service.addInfo.AddInfoService;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.dateSession.DateSessionService;
import cg.tcarespb.service.employee.request.*;
import cg.tcarespb.service.employee.response.EmployeeDateSessionListResponse;
import cg.tcarespb.service.employee.response.EmployeeDetailInFilterListResponse;
import cg.tcarespb.service.employee.response.EmployeeDetailResponse;
import cg.tcarespb.service.employee.response.EmployeeListResponse;
import cg.tcarespb.service.location.LocationPalaceService;
import cg.tcarespb.service.serviceGeneral.ServiceGeneralService;
import cg.tcarespb.service.skill.SkillService;
import cg.tcarespb.util.AppMessage;
import cg.tcarespb.util.AppUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeAddInfoRepository employeeAddInfoRepository;
    private final DateSessionService dateSessionService;
    private final EmployeeServiceGeneralRepository employeeServiceGeneralRepository;
    private final AccountRepository accountRepository;
    private final AddInfoService addInfoService;
    private final SkillService skillService;
    private final ServiceGeneralService serviceGeneralService;
    private final LocationPalaceService locationPalaceService;
    private final LocationPalaceRepository locationPalaceRepository;
    private final DateSessionRepository dateSessionRepository;
    private final CartService cartService;
    private final PhotoRepository photoRepository;


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

    @Transactional
    public void updateDateSessionEmployee(EmployeeDateSessionListResponse req, String employeeId) {
        dateSessionRepository.deleteAllByEmployeeId(employeeId);

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

//    public String createAccountEmployee(EmployeeAccountSaveRequest request) {
//
//        Account account = new Account();
//        // validate
//        account.setEmail(request.getEmail());
//        account.setPassword(request.getPassword());
//        account.setERole(ERole.ROLE_EMPLOYEE);
//        accountRepository.save(account);
//        Employee employee = new Employee();
//        employee.setGender(EGender.valueOf(request.getGender()));
//        employee.setFirstName(request.getFirstName());
//        employee.setLastName(request.getLastName());
//        employee.setPersonID(request.getPersonID());
//        employee.setStatus(EStatus.WAITING);
//        employeeRepository.save(employee);
//        account.setEmployee(employee);
//        accountRepository.save(account);
//        return employee.getId();
//    }

    public void updateBioEmployee(EmployeeBioSaveRequest request, String employeeId) {
        Employee employee = findById(employeeId);
        employee.setBioTitle(request.getBioTitle());
        employee.setDescriptionAboutMySelf(request.getDescriptionAboutMySelf());
        employeeRepository.save(employee);

    }
    public void updatePhotoEmployee(EmployeeAvatarSaveRequest request, String employeeId) {
        Employee employee = findById(employeeId);
        Photo image = photoRepository.findPhotoById(request.getAvatar()).get();
        employee.setPhoto(image);
//        employee.setDescriptionAboutMySelf(request.getDescriptionAboutMySelf());
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


    public Employee findById(String id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Employee", id)));
    }

    @Transactional
    public void edit(EmployeeEditRequest request, String id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Employee", id)));
        employee.setAddress(request.getAddress());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setDescriptionAboutMySelf(request.getDescriptionAboutMySelf());
        employee.setBioTitle(request.getBioTitle());
        employee.setPersonID(request.getPersonID());
        employee.setGender(EGender.valueOf(request.getGender()));
        employee.setStatus(EStatus.valueOf(request.getStatus()));
        employee.setExperience(EExperience.valueOf(request.getExperience()));
        employee.setEducation(EEducation.valueOf(request.getEducation()));
        employeeRepository.save(employee);

        employeeSkillRepository.deleteAllById(employee.getEmployeeSkills().stream()
                .map(EmployeeSkill::getId)
                .collect(Collectors.toList()));
        var employeeSkills = new ArrayList<EmployeeSkill>();
        for (String idSkill : request.getIdSkills()) {
            Skill skill = new Skill(String.valueOf(idSkill));
            employeeSkills.add(new EmployeeSkill(employee, skill));
        }
        employeeSkillRepository.saveAll(employeeSkills);

        employeeAddInfoRepository.deleteAllById(employee.getEmployeeInfos().stream()
                .map(EmployeeInfo::getId)
                .collect(Collectors.toList()));
        var employeeInfos = new ArrayList<EmployeeInfo>();
        for (String idInfo : request.getIdAddInfos()) {
            AddInfo info = new AddInfo(String.valueOf(idInfo));
            employeeInfos.add(new EmployeeInfo(employee, info));
        }
        employeeAddInfoRepository.saveAll(employeeInfos);

        employeeServiceGeneralRepository.deleteAllById(employee.getEmployeeServiceGenerals().stream()
                .map(EmployeeServiceGeneral::getId)
                .collect(Collectors.toList()));
        var employeeServices = new ArrayList<EmployeeServiceGeneral>();
        for (String idService : request.getIdServices()) {
            ServiceGeneral serviceGeneral = new ServiceGeneral(String.valueOf(idService));
            employeeServices.add(new EmployeeServiceGeneral(employee, serviceGeneral));
        }
        employeeServiceGeneralRepository.saveAll(employeeServices);
    }


    public void createEmployeeFilter(EmployeeSaveFilterRequest req) {
        Employee employee = new Employee();
        employeeRepository.save(employee);
        employee.setEmployeeInfos(req.getListInfoId().stream().map(e -> {
            EmployeeInfo employeeInfo = new EmployeeInfo();
            AddInfo addInfo = addInfoService.findByIdForEdit(e);
            employeeInfo.setEmployee(employee);
            employeeInfo.setAddInfo(addInfo);
            employeeAddInfoRepository.save(employeeInfo);
            return employeeInfo;
        }).collect(Collectors.toList()));
        employee.setEmployeeSkills(req.getListSkillId().stream().map(e -> {
            EmployeeSkill employeeSkill = new EmployeeSkill();
            Skill skill = skillService.findByIdForEdit(e);
            employeeSkill.setEmployee(employee);
            employeeSkill.setSkill(skill);
            employeeSkillRepository.save(employeeSkill);
            return employeeSkill;
        }).collect(Collectors.toList()));

        employee.setEmployeeServiceGenerals(req.getListServiceId().stream().map(e -> {
            EmployeeServiceGeneral employeeServiceGeneral = new EmployeeServiceGeneral();
            ServiceGeneral serviceGeneral = serviceGeneralService.findById(e);
            employeeServiceGeneral.setService(serviceGeneral);
            employeeServiceGeneral.setEmployee(employee);
            employeeServiceGeneralRepository.save(employeeServiceGeneral);
            return employeeServiceGeneral;
        }).collect(Collectors.toList()));
        employee.setStatus(EStatus.valueOf(req.getStatus()));
        LocationPlace locationPlace = new LocationPlace();
        locationPlace.setLongitude(Double.valueOf(req.getLongitude()));
        locationPlace.setLatitude(Double.valueOf(req.getLatitude()));
        locationPlace.setName(req.getNameLocation());
        locationPlace.setDistanceForWork(Double.valueOf(req.getDistanceForWork()));
        locationPlace.setEmployee(employee);
        employee.setLocationPlace(locationPalaceService.create(locationPlace));
        employeeRepository.save(employee);
    }

    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    public void updateLocationForEmployee(EmployeeLocationSaveRequest request, String id) {
        Employee employee = findById(id);
        LocationPlace locationPalace = new LocationPlace();
        locationPalace.setName(request.getNameLocation());
        locationPalace.setDistanceForWork(Double.valueOf(request.getDistanceForWork()));
        locationPalace.setLatitude(Double.valueOf(request.getLatitude()));
        locationPalace.setLongitude(Double.valueOf(request.getLongitude()));
        locationPalace.setEmployee(employee);
        locationPalaceRepository.save(locationPalace);
        employee.setLocationPlace(locationPalace);
        employeeRepository.save(employee);
    }

    public EmployeeDetailInFilterListResponse findEmployeeDetailById(String idEmployee, String idCart) {
        Employee employee = findById(idEmployee);
        Cart cart = cartService.findById(idCart);
        EmployeeDetailInFilterListResponse employeeDetail = new EmployeeDetailInFilterListResponse();
        employeeDetail.setId(idEmployee);
        employeeDetail.setAddress(employee.getLocationPlace().getName());
        employeeDetail.setExperience(employee.getExperience());
        employeeDetail.setInfoName(employee.getEmployeeInfos().stream().map(e -> e.getAddInfo().getName()).collect(Collectors.toList()));
        employeeDetail.setServiceName(employee.getEmployeeServiceGenerals().stream().map(e -> e.getService().getName()).collect(Collectors.toList()));
        employeeDetail.setSkillName(employee.getEmployeeSkills().stream().map(e -> e.getSkill().getName()).collect(Collectors.toList()));
        employeeDetail.setFirstName(employee.getFirstName());
        employeeDetail.setLastName(employee.getLastName());
        employeeDetail.setDescriptionAboutMySelf(employee.getDescriptionAboutMySelf());
        employeeDetail.setDistanceToWork(locationPalaceService.getDistance(employee.getLocationPlace().getLatitude(),
                employee.getLocationPlace().getLongitude(),
                cart.getLocationPlace().getLatitude(),
                cart.getLocationPlace().getLongitude()));
        return employeeDetail;
    }

}
