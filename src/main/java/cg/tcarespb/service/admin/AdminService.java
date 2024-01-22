package cg.tcarespb.service.admin;

import cg.tcarespb.models.Account;
import cg.tcarespb.models.Employee;
import cg.tcarespb.models.Saler;
import cg.tcarespb.models.User;
import cg.tcarespb.models.enums.EStatus;
import cg.tcarespb.repository.*;
import cg.tcarespb.service.admin.request.AdminStartEndDayRequest;
import cg.tcarespb.service.admin.response.AdminEmployeeResponse;
import cg.tcarespb.service.admin.response.AdminRevenueResponse;
import cg.tcarespb.service.admin.response.AdminSalerResponse;
import cg.tcarespb.service.admin.response.AdminUserResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminService {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final HistoryWorkingRepository historyWorkingRepository;
    private final ContractRepository contractRepository;
    private final AccountRepository accountRepository;
    private final SalerRepository salerRepository;

    public Page<AdminUserResponse> getAllUser(Pageable pageable, Boolean deleted) {
        return userRepository.getAllUser(pageable, deleted);
    }

    public Page<AdminSalerResponse> getAllSaler(Pageable pageable, Boolean deleted) {
        return salerRepository.getAllSaler(deleted, pageable);
    }

    public Page<AdminEmployeeResponse> getALlEmployeeByStatus(Pageable pageable, EStatus status) {
        return employeeRepository.getAllEmployeeByStatus(status, pageable);
    }

    @Transactional
    public void banEmployee(String idEmpoyee) {
        Employee employee = employeeRepository.findById(idEmpoyee).orElse(null);
        employee.setStatus(EStatus.BAN);
        accountRepository.deleteAccountByEmployeeId(idEmpoyee);
        employeeRepository.save(employee);
    }

    @Transactional
    public void unBanEmployee(String idEmpoyee) {
        Employee employee = employeeRepository.findById(idEmpoyee).orElse(null);
        employee.setStatus(EStatus.ACTIVE);
        Account account = accountRepository.findAccountByEmployeeId(idEmpoyee);
        account.setDeleted(false);
        accountRepository.save(account);
        employeeRepository.save(employee);
    }

    @Transactional
    public void banSaler(String idSaler) {
        Saler saler = salerRepository.findById(idSaler).orElse(null);
        saler.setDeleted(true);
        accountRepository.deleteAccountBySalerId(idSaler);
        salerRepository.save(saler);
    }

    @Transactional
    public void unBanSaler(String idSaler) {
        Saler saler = salerRepository.findById(idSaler).orElse(null);
        saler.setDeleted(false);
        Account account = accountRepository.findAccountBySalerId(idSaler);
        account.setDeleted(false);
        accountRepository.save(account);
        salerRepository.save(saler);
    }

    @Transactional
    public void banUser(String idUser) {
        userRepository.deleteById(idUser);
        accountRepository.deleteAccountByUserId(idUser);
    }

    @Transactional
    public void unBanUser(String idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        user.setDeleted(false);
        userRepository.save(user);
        Account account = accountRepository.findAccountByUserId(idUser);
        account.setDeleted(false);
        accountRepository.save(account);
    }

    public void activeEmployee(String idEmpoyee) {
        Employee employee = employeeRepository.findById(idEmpoyee).orElse(null);
        employee.setStatus(EStatus.ACTIVE);
        employeeRepository.save(employee);
    }
}

