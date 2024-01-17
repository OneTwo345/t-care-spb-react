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
import cg.tcarespb.service.admin.request.AdminStartEndDayRequest;
import cg.tcarespb.service.cart.response.CartListResponse;
import cg.tcarespb.service.contract.ContractService;
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
    private final ContractService contractService;


    @GetMapping("/users")
    public ResponseEntity<?> getAllUser(Pageable pageable) {
        return new ResponseEntity<>(adminService.getAllUser(pageable, false), HttpStatus.OK);
    }

    @GetMapping("/users/ban")
    public ResponseEntity<?> getAllUserBan(Pageable pageable) {
        return new ResponseEntity<>(adminService.getAllUser(pageable, true), HttpStatus.OK);
    }

    @PutMapping("/users/ban/{idUser}")
    public ResponseEntity<?> banUsers(@PathVariable("idUser") String idUser) {
        adminService.banUser(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users/unBan/{idUser}")
    public ResponseEntity<?> unBanUsers(@PathVariable("idUser") String idUser) {
        adminService.unBanUser(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/employees/ban/{idEmployee}")
    public ResponseEntity<?> banEmployee(@PathVariable("idEmployee") String idEmployee) {
        adminService.banEmployee(idEmployee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/employees/unBan/{idEmployee}")
    public ResponseEntity<?> unBanEmployee(@PathVariable("idEmployee") String idEmployee) {
        adminService.unBanEmployee(idEmployee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/salers")
    public ResponseEntity<?> getAllSaler(Pageable pageable) {
        return new ResponseEntity<>(adminService.getAllSaler(pageable, false), HttpStatus.OK);
    }

    @GetMapping("/salers/ban")
    public ResponseEntity<?> getAllSalerBan(Pageable pageable) {
        return new ResponseEntity<>(adminService.getAllSaler(pageable, true), HttpStatus.OK);
    }

    @PutMapping("/salers/ban/{idSaler}")
    public ResponseEntity<?> banSaler(@PathVariable("idSaler") String idSaler) {
        adminService.banSaler(idSaler);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/salers/unBan/{idSaler}")
    public ResponseEntity<?> unBanSaler(@PathVariable("idSaler") String idSaler) {
        adminService.unBanSaler(idSaler);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/employees/active/{idEmployee}")
    public ResponseEntity<?> active(@PathVariable("id") String idEmployee) {
        adminService.activeEmployee(idEmployee);
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
        return new ResponseEntity<>(adminService.getALlEmployeeByStatus(pageable, EStatus.WAITING), HttpStatus.OK);
    }

    @GetMapping("/employees/active")
    public ResponseEntity<?> getAllEmployeeActive(Pageable pageable) {
        return new ResponseEntity<>(adminService.getALlEmployeeByStatus(pageable, EStatus.ACTIVE), HttpStatus.OK);
    }

    @GetMapping("/employees/ban")
    public ResponseEntity<?> getAllEmployeeBan(Pageable pageable) {
        return new ResponseEntity<>(adminService.getALlEmployeeByStatus(pageable, EStatus.BAN), HttpStatus.OK);
    }

    @GetMapping("/revenue/contact")
    public ResponseEntity<?> getRevenueFromContact() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/revenue/contract")
    public ResponseEntity<?> getRevenueFromContract(@RequestBody  AdminStartEndDayRequest req) {
        return new ResponseEntity<>( contractService.calculateRevenue(req),HttpStatus.OK);
    }
    @GetMapping("/revenue")
    public ResponseEntity<?> getRevenue() {
//        return new ResponseEntity<>(adminService.getALlEmployeeByStatus(pageable, EStatus.BAN), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
