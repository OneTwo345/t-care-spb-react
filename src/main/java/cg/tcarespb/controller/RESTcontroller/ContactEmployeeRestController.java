package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.models.enums.EContactStatus;
import cg.tcarespb.service.contactEmployee.ContactEmployeeService;
import cg.tcarespb.service.contactEmployee.request.ContactEmployeeByCartSaveRequest;
import cg.tcarespb.service.contactEmployee.request.ContactEmployeeDateSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contactEmployees")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ContactEmployeeRestController {
    private final ContactEmployeeService contactEmployeeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ContactEmployeeByCartSaveRequest request) {
        contactEmployeeService.createContactEmployee(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/date")
    public ResponseEntity<?> updateDateForContactEmployee(@RequestBody ContactEmployeeDateSaveRequest request) {
        contactEmployeeService.updateDateForContactEmployee(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/employees/confirming/{idContact}")
    public ResponseEntity<?> confirmContactEmployee(@PathVariable("idContact") String idContact) {
        contactEmployeeService.updateContactStatus(idContact, EContactStatus.NOTMET);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/employees/canceled/{idContact}")
    public ResponseEntity<?> canceledContactEmployee(@PathVariable("idContact") String idContact) {
        contactEmployeeService.updateContactStatus(idContact, EContactStatus.CANCELED);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("employees/confirming/{idEmployee}")
    public ResponseEntity<?> getAllContactEmployeeConfirmingByEmployee(@PathVariable("idEmployee") String idEmployee, Pageable pageable) {
        contactEmployeeService.getAllContactEmployeeByEmployeeId(pageable, idEmployee, EContactStatus.CONFIRMING);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("employees/notMetContact/{idEmployee}")
    public ResponseEntity<?> getAllContactEmployeeNotMetByEmployee(@PathVariable("idEmployee") String idEmployee, Pageable pageable) {
        contactEmployeeService.getAllContactEmployeeByEmployeeId(pageable, idEmployee, EContactStatus.NOTMET);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("employees/metContact/{idEmployee}")
    public ResponseEntity<?> getAllContactEmployeeMetByEmployee(@PathVariable("idEmployee") String idEmployee, Pageable pageable) {
        contactEmployeeService.getAllContactEmployeeByEmployeeId(pageable, idEmployee, EContactStatus.MET);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
