package cg.tcarespb.service.contract;

import cg.tcarespb.models.*;
import cg.tcarespb.repository.ContractRepository;
import cg.tcarespb.repository.EmployeeRepository;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.contract.request.ContractEditRequest;
import cg.tcarespb.service.contract.request.ContractSaveRequest;
import cg.tcarespb.service.contract.response.ContractDetailResponse;
import cg.tcarespb.service.contract.response.ContractListResponse;
import cg.tcarespb.util.AppMessage;
import cg.tcarespb.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final EmployeeRepository employeeRepository;
    private final CartService cartService;
    public List<ContractListResponse> getContractList(){
        return contractRepository.findAll()
                .stream()
                .map(contract -> ContractListResponse.builder()
                        .id(contract.getId())
                        .timeStart(contract.getTimeStart())
                        .timeEnd(contract.getTimeEnd())
                        .namePatient(contract.getNamePatient())
                        .agePatient(contract.getAgePatient())
                        .content(contract.getContent())
                        .totalPrice(contract.getTotalPrice())
                        .employeeName(contract.getEmployee().getFirstName())
                        .build())
                .collect(Collectors.toList());
    }
    public void create(ContractSaveRequest request) {
        var contract = AppUtil.mapper.map(request, Contract.class);
        Optional<Employee> employee = employeeRepository.findById(request.getEmployeeId());
        contract.setEmployee(employee.get());
         contractRepository.save(contract);
    }
    public String createContract(String idCart, String idEmployee){
        Cart cart = cartService.findById(idCart);
        Employee employee= employeeRepository.findById(idEmployee).get();
        Contract contract  = new Contract();
        contract.setTimeStart(cart.getTimeStart());
        contract.setTimeEnd(cart.getTimeEnd());
        contract.setEmployee(employee);
        contract.setNameService(cart.getService().getName());
        contract.setPriceService(cart.getService().getPriceEmployee());
        contract.setNamePatient(cart.getNamePatient());
        contract.setAgePatient(cart.getAgePatient());
        contract.setTotalPrice(cart.getService().getTotalPrice());
  return null;
    }

    public Contract findById(String id) {
        return contractRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Contract", id)));
    }

    public ContractDetailResponse findDetailContractById(String id){
        var contract = contractRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Contract", id)));

        var result = AppUtil.mapper.map(contract,ContractDetailResponse.class);
        Optional<Employee> employee = employeeRepository.findById(contract.getEmployee().getId());
        result.setEmployeeName(employee.get().getFirstName());

        return result;
    }

    public void edit(ContractEditRequest request, String id){
        Contract contract = contractRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Contract", id)));
        contract.setTimeStart(LocalDate.parse(request.getTimeStart()));
        contract.setTimeEnd(LocalDate.parse(request.getTimeEnd()));
        contract.setNamePatient(request.getNamePatient());
        contract.setAgePatient(Integer.valueOf(request.getAgePatient()));
        contract.setContent(request.getContent());
        contract.setTotalPrice(new BigDecimal(request.getTotalPrice()));
        Optional<Employee> employee = employeeRepository.findById(request.getEmployeeId());
        contract.setEmployee(employee.get());
        contractRepository.save(contract);
    }
    public Contract create(Contract contract){
        return contractRepository.save(contract);
    }
}
