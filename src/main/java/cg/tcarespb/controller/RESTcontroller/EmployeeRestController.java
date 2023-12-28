package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.service.employee.EmployeeService;
import cg.tcarespb.service.employee.request.EmployeeSaveRequest;
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

    @PostMapping
    public void create(@RequestBody EmployeeSaveRequest request){
        employeeService.create(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        employeeService.delete(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
