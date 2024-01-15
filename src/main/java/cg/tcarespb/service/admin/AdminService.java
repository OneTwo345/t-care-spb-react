package cg.tcarespb.service.admin;

import cg.tcarespb.models.Employee;
import cg.tcarespb.models.enums.EStatus;
import cg.tcarespb.repository.ContractRepository;
import cg.tcarespb.repository.EmployeeRepository;
import cg.tcarespb.repository.HistoryWorkingRepository;
import cg.tcarespb.repository.UserRepository;
import cg.tcarespb.service.admin.response.AdminEmployeeResponse;
import cg.tcarespb.service.admin.response.AdminUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminService {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final HistoryWorkingRepository historyWorkingRepository;
    private final ContractRepository contractRepository;

    public Page<AdminUserResponse> getAllUser(Pageable pageable) {
        return userRepository.getAllUser(pageable);
    }

    public Page<AdminEmployeeResponse> getAllEmployee(Pageable pageable) {
        return employeeRepository.getAllEmployee(pageable);
    }

    public Page<AdminEmployeeResponse> getAllEmployeeWaiting(Pageable pageable) {
        return employeeRepository.getAllEmployeeByStatus(EStatus.WAITING, pageable);
    }

    public Page<AdminEmployeeResponse> getAllEmployeeActive(Pageable pageable) {
        return employeeRepository.getAllEmployeeByStatus(EStatus.ACTIVE, pageable);
    }

    public Page<AdminEmployeeResponse> getAllEmployeeBan(Pageable pageable) {
        return employeeRepository.getAllEmployeeByStatus(EStatus.BAN, pageable);
    }

    public void banEmployee(String idEmpoyee) {
        Employee employee = employeeRepository.findById(idEmpoyee).orElse(null);
        employee.setStatus(EStatus.BAN);
        employeeRepository.save(employee);
    }
    public void activeEmployee(String idEmpoyee) {
        Employee employee = employeeRepository.findById(idEmpoyee).orElse(null);
        employee.setStatus(EStatus.ACTIVE);
        employeeRepository.save(employee);
    }

    public void banUser(String idUser) {
        userRepository.deleteById(idUser);
    }
}
