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

    @PostMapping("/user")
    public ResponseEntity<?> createByUser(@RequestBody ContactEmployeeByCartSaveRequest request) {
        contactEmployeeService.createContactEmployeeByUser(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/saler")
    public ResponseEntity<?> createBySaler(@RequestBody ContactEmployeeByCartSaveRequest request) {
        contactEmployeeService.createContactEmployee(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/date")
    public ResponseEntity<?> updateDateForContactEmployee(@RequestBody ContactEmployeeDateSaveRequest request) {
        contactEmployeeService.updateDateForContactEmployee(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/employees/confirmed/{idContact}")
    public ResponseEntity<?> confirmContactEmployee(@PathVariable("idContact") String idContact) {
        contactEmployeeService.updateContactStatus(idContact, EContactStatus.CONFIRMED);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/employees/canceled/{idContact}")
    public ResponseEntity<?> canceledContactEmployee(@PathVariable("idContact") String idContact) {
        contactEmployeeService.updateContactStatus(idContact, EContactStatus.CANCELED);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/employees/confirming/{idEmployee}")
    public ResponseEntity<?> getAllContactEmployeeConfirmingByEmployee(@PathVariable("idEmployee") String idEmployee, Pageable pageable) {
        return new ResponseEntity<>(contactEmployeeService.getAllContactEmployeeByEmployeeId(pageable, idEmployee, EContactStatus.CONFIRMING), HttpStatus.OK);
    }

    @GetMapping("/employees/confirmed/{idEmployee}")
    public ResponseEntity<?> getAllContactEmployeeNotMetByEmployee(@PathVariable("idEmployee") String idEmployee, Pageable pageable) {
        return new ResponseEntity<>(contactEmployeeService.getAllContactEmployeeByEmployeeId(pageable, idEmployee, EContactStatus.CONFIRMED), HttpStatus.OK);
    }
    @GetMapping("/confirming")
    public ResponseEntity<?> getAllConfirmingContact(Pageable pageable) {
        return new ResponseEntity<>(contactEmployeeService.getAllContactEmployeeByStatus(pageable, EContactStatus.CONFIRMING), HttpStatus.OK);
    }
    @GetMapping("/confirmed")
    public ResponseEntity<?> getAllConfirmedContact(Pageable pageable) {
        return new ResponseEntity<>(contactEmployeeService.getAllContactEmployeeByStatus(pageable, EContactStatus.CONFIRMED), HttpStatus.OK);
    }
    @GetMapping("/canceled")
    public ResponseEntity<?> getAllCanceledContact(Pageable pageable) {
        return new ResponseEntity<>(contactEmployeeService.getAllContactEmployeeByStatus(pageable, EContactStatus.CANCELED), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllContact(Pageable pageable) {
        return new ResponseEntity<>(contactEmployeeService.getAllContact(pageable), HttpStatus.OK);
    }
}