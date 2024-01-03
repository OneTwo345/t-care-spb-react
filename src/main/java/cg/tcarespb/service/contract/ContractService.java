package cg.tcarespb.service.contract;

import cg.tcarespb.models.*;
import cg.tcarespb.repository.ContractRepository;
import cg.tcarespb.repository.EmployeeRepository;
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
                        .pricePerHour(contract.getPricePerHour())
                        .totalPrice(contract.getTotalPrice())
                        .dateQuantity(contract.getDateQuantity())
                        .hourPerDay(contract.getHourPerDay())
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
        contract.setPricePerHour(new BigDecimal(request.getPricePerHour()));
        contract.setTotalPrice(new BigDecimal(request.getTotalPrice()));
        contract.setDateQuantity(Integer.valueOf(request.getDateQuantity()));
        contract.setHourPerDay(Integer.valueOf(request.getHourPerDay()));
        Optional<Employee> employee = employeeRepository.findById(request.getEmployeeId());
        contract.setEmployee(employee.get());
        contractRepository.save(contract);

    }
}
