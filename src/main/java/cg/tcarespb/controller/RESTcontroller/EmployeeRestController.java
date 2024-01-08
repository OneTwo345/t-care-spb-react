package cg.tcarespb.controller.RESTcontroller;


import cg.tcarespb.service.customMail.EmailSenderService;
import cg.tcarespb.service.employee.EmployeeService;
import cg.tcarespb.service.employee.request.*;
import cg.tcarespb.service.employee.response.EmployeeDateSessionListResponse;
import cg.tcarespb.service.employee.response.EmployeeDetailInFilterListResponse;
import cg.tcarespb.service.employee.response.EmployeeDetailResponse;
import cg.tcarespb.service.employee.response.EmployeeListResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
@CrossOrigin("http://localhost:3000")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    private  final EmailSenderService emailSenderService;

    @GetMapping
    public ResponseEntity<List<EmployeeListResponse>> getEmployeeList(){
        List<EmployeeListResponse> employeeListResponseList = employeeService.getEmployeeList();
        return ResponseEntity.ok(employeeListResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailResponse> getEmployeeDetail(@PathVariable("id") String id){
        EmployeeDetailResponse employee = employeeService.findDetailEmployeeById(id);
        return ResponseEntity.ok(employee);
    }



    @PostMapping
    public void create(@RequestBody EmployeeSaveRequest request){
        employeeService.create(request);
    }

    @PostMapping("/schedule")
    public void createEmployeeSchedule(@RequestBody EmployeeScheduleSaveRequest request){
        employeeService.createScheduleEmployee(request);
    }

    @PostMapping("/account")
    public ResponseEntity<?> createEmployeeAccount(@RequestBody EmployeeAccountSaveRequest request){
      String employeeId =  employeeService.createAccountEmployee(request);
         return new ResponseEntity<>(employeeId, HttpStatus.CREATED);
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
    @PutMapping("/schedule/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable("id") String id, @RequestBody EmployeeScheduleSaveRequest req) {
        employeeService.updateScheduleEmployee(req, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/bio/{id}")
    public ResponseEntity<?> updateBio(@PathVariable("id") String id, @RequestBody EmployeeBioSaveRequest req) {
        employeeService.updateBioEmployee(req, id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/jobType/{id}")
    public ResponseEntity<?> updateJobType(@PathVariable("id") String id, @RequestBody EmployeeJobTypeSaveRequest req) {
        employeeService.updateJobType(req, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/location/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable("id") String id, @RequestBody EmployeeLocationSaveRequest req) {
        employeeService.updateLocationForEmployee(req, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        employeeService.delete(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PostMapping("/filterCreate")
    public  ResponseEntity<?> createFilterEmployee(@RequestBody EmployeeSaveFilterRequest req){
        emailSenderService.sendEmail("quochuy248@gmail.com","this is subject","this is body");
        employeeService.createEmployeeFilter(req);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/detail/{idEmployee}")
    public ResponseEntity<EmployeeDetailInFilterListResponse> getEmployeeDetailInFilter(@PathVariable("idEmployee") String idEmployee, @RequestBody String idCart){
        EmployeeDetailInFilterListResponse employee = employeeService.findEmployeeDetailById(idEmployee,idCart);
        return ResponseEntity.ok(employee);
    }

    // Cho phép các phương thức liên quan tới CORS
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        return ResponseEntity.ok().build();
    }



}
