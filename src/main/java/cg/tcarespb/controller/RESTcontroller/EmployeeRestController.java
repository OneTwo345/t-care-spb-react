package cg.tcarespb.controller.RESTcontroller;


import cg.tcarespb.models.Employee;
import cg.tcarespb.models.enums.EStatus;
import cg.tcarespb.service.customMail.EmailSenderService;
import cg.tcarespb.service.employee.EmployeeService;
import cg.tcarespb.service.employee.request.*;
import cg.tcarespb.service.employee.response.EmployeeDateSessionListResponse;
import cg.tcarespb.service.employee.response.EmployeeDetailInFilterListResponse;
import cg.tcarespb.service.employee.response.EmployeeDetailResponse;
import cg.tcarespb.service.employee.response.EmployeeListResponse;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
@CrossOrigin("http://localhost:3000")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    private final EmailSenderService emailSenderService;

    @GetMapping
    public ResponseEntity<Page<EmployeeListResponse>> getEmployeeList(@PageableDefault(size = 5)Pageable pageable) {
        Page<EmployeeListResponse> employeeListResponseList = employeeService.getEmployeeList(EStatus.ACTIVE,pageable);

        return ResponseEntity.ok(employeeListResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeDetail(@PathVariable("id") String id) {
        EmployeeDetailResponse employee = employeeService.findDetailEmployeeById(id);
        return ResponseEntity.ok(employee);
    }


    @PostMapping
    public void create(@RequestBody EmployeeSaveRequest request) {
        employeeService.create(request);
    }

    @PostMapping("/schedule")
    public void createEmployeeSchedule(@RequestBody EmployeeScheduleSaveRequest request) {
        employeeService.createScheduleEmployee(request);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> edit(@PathVariable("id") String id, @RequestBody EmployeeEditRequest request) {
        employeeService.edit(request, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/dateSessions/{id}")
    public ResponseEntity<?> updateDateSession(@PathVariable("id") String id, @RequestBody EmployeeDateSessionListResponse req) {
        employeeService.updateDateSessionEmployee(req, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/experience/{id}")
    public ResponseEntity<?> updateExperience(@PathVariable("id") String id, @RequestBody EmployeeExperienceSaveRequest req) {
        employeeService.updateExperienceEmployee(req, id);
        return ResponseEntity.noContent().build();
    }
//    @PutMapping("/schedule/{id}")
//    public ResponseEntity<?> updateSchedule(@PathVariable("id") String id, @RequestBody EmployeeScheduleSaveRequest req) {
//        employeeService.updateScheduleEmployee(req, id);
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/bio/{id}")
    public ResponseEntity<?> updateBio(@PathVariable("id") String id, @RequestBody EmployeeBioSaveRequest req) {
        employeeService.updateBioEmployee(req, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/photo/{id}")
    public ResponseEntity<?> updatePhoto(@PathVariable("id") String id, @RequestBody EmployeeAvatarSaveRequest req) {
        employeeService.updatePhotoEmployee(req, id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/location/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable("id") String id, @RequestBody EmployeeLocationSaveRequest req) {
        employeeService.updateLocationForEmployee(req, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/status/ban/{id}")
    public ResponseEntity<?> updateBanStatusEmployee(@PathVariable("id") String id) {
        employeeService.updateStatusForEmployee(id, EStatus.BAN);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/status/active/{id}")
    public ResponseEntity<?> updateActiveStatusEmployee(@PathVariable("id") String id) {
        employeeService.updateStatusForEmployee(id, EStatus.ACTIVE);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        employeeService.delete(id);
        return ResponseEntity.ok("Xóa nhân viên thành công");
    }

    @PostMapping("/filterCreate")
    public ResponseEntity<?> createFilterEmployee(@RequestBody EmployeeSaveFilterRequest req) {
//        emailSenderService.sendEmail("quochuy248@gmail.com","this is subject","this is body");
        String idEmployee = employeeService.createEmployeeFilter(req);
        return new ResponseEntity<>(idEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/detail/{idEmployee}")
    public ResponseEntity<EmployeeDetailInFilterListResponse> getEmployeeDetailInFilter(@PathVariable("idEmployee") String idEmployee, @RequestBody String idCart) {
        EmployeeDetailInFilterListResponse employee = employeeService.findEmployeeDetailById(idEmployee, idCart);
        return ResponseEntity.ok(employee);
    }


    // Cho phép các phương thức liên quan tới CORS
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        return ResponseEntity.ok().build();
    }


}
