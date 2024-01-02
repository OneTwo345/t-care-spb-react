package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.service.cart.request.CartDateSessionListSaveRequest;
import cg.tcarespb.service.employee.EmployeeService;
import cg.tcarespb.service.employee.request.*;
import cg.tcarespb.service.employee.response.EmployeeDateSessionListResponse;
import cg.tcarespb.service.employee.response.EmployeeDetailResponse;
import cg.tcarespb.service.employee.response.EmployeeListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private final EmployeeService employeeService;

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

    @PutMapping("/account/{id}")
    public ResponseEntity<?> updateAccountEmployee(@PathVariable("id") String id, @RequestBody EmployeeAccountSaveRequest req) {
        employeeService.updateAccountEmployee(req, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/bio/{id}")
    public ResponseEntity<?> updateBio(@PathVariable("id") String id, @RequestBody EmployeeBioSaveRequest req) {
        employeeService.updateBioEmployee(req, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        employeeService.delete(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
