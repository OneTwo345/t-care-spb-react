package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.models.Account;
import cg.tcarespb.models.Cart;
import cg.tcarespb.models.Employee;
import cg.tcarespb.models.Saler;
import cg.tcarespb.models.enums.EGender;
import cg.tcarespb.models.enums.ERole;
import cg.tcarespb.models.enums.EStatus;
import cg.tcarespb.repository.AccountRepository;
import cg.tcarespb.repository.SalerRepository;
import cg.tcarespb.service.admin.AdminService;
import cg.tcarespb.service.admin.request.AdminSaveSalerRequest;
import cg.tcarespb.service.cart.response.CartListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AdminRestController {
    private final AdminService adminService;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final SalerRepository salerRepository;


    @GetMapping("/users")
    public ResponseEntity<?> getAllUser(Pageable pageable) {
        return new ResponseEntity<>(adminService.getAllUser(pageable), HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployee(Pageable pageable) {
        return new ResponseEntity<>(adminService.getAllEmployee(pageable), HttpStatus.OK);
    }

    @PutMapping("/users/ban/{id}")
    public ResponseEntity<?> banUsers(@PathVariable("id") String id) {
        adminService.banUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/employees/ban/{id}")
    public ResponseEntity<?> banEmployee(@PathVariable("id") String id) {
        adminService.banEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/employees/active/{id}")
    public ResponseEntity<?> active(@PathVariable("id") String id) {
        adminService.activeEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/salers/account")
    public ResponseEntity<?> createSaler(@RequestBody AdminSaveSalerRequest req) {
        if (accountRepository.existsByEmailIgnoreCase(req.getEmail()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email Đã Tồn Tại");
        Account account = new Account();
        account.setEmail(req.getEmail());
        account.setPassword(passwordEncoder.encode(req.getPassword()));
        account.setERole(ERole.ROLE_SALER);
        accountRepository.save(account);
        Saler saler = new Saler();
        saler.setGender(EGender.valueOf(req.getGender()));
        saler.setFirstName(req.getFirstName());
        saler.setLastName(req.getLastName());
        saler.setPersonID(req.getPersonID());
        saler.setPhoneNumber(req.getPhoneNumber());
        salerRepository.save(saler);
        account.setSaler(saler);
        accountRepository.save(account);
        String salerId = saler.getId();
        return new ResponseEntity<>(salerId, HttpStatus.CREATED);
    }
    @GetMapping("/employees/waiting")
    public ResponseEntity<?> getAllEmployeeWaiting(Pageable pageable) {
        return new ResponseEntity<>(adminService.getAllEmployeeWaiting(pageable), HttpStatus.OK);
    }
    @GetMapping("/employees/active")
    public ResponseEntity<?> getAllEmployeeActive(Pageable pageable) {
        return new ResponseEntity<>(adminService.getAllEmployeeActive(pageable), HttpStatus.OK);
    }
    @GetMapping("/employees/ban")
    public ResponseEntity<?> getAllEmployeeBan(Pageable pageable) {
        return new ResponseEntity<>(adminService.getAllEmployeeBan(pageable), HttpStatus.OK);
    }

}
