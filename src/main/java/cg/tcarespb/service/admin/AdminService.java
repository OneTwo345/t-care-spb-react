package cg.tcarespb.service.admin;

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

    public void banEmployee(String idEmpoyee) {
        employeeRepository.deleteById(idEmpoyee);
    }
    public void banUser(String idUser) {
        userRepository.deleteById(idUser);
    }
}
