package cg.tcarespb.service.contract;

import cg.tcarespb.models.*;
import cg.tcarespb.repository.ContractRepository;
import cg.tcarespb.repository.EmployeeRepository;
import cg.tcarespb.repository.LocationPalaceRepository;
import cg.tcarespb.service.admin.request.AdminStartEndDayRequest;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.contract.request.ContractEditRequest;
import cg.tcarespb.service.contract.request.ContractSaveRequest;
import cg.tcarespb.service.contract.response.ContractDetailResponse;
import cg.tcarespb.service.contract.response.ContractListResponse;
import cg.tcarespb.service.contract.response.ContractRevenueContractResponse;
import cg.tcarespb.service.historyWorking.HistoryWorkingService;
import cg.tcarespb.util.AppConvertString;
import cg.tcarespb.util.AppMessage;
import cg.tcarespb.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final EmployeeRepository employeeRepository;
    private final CartService cartService;
    private final HistoryWorkingService historyWorkingService;
    private final LocationPalaceRepository locationPalaceRepository;

    public List<ContractListResponse> getContractList() {
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

    public String createContract(String cartId) {
        Cart cart = cartService.findById(cartId);
        Employee employee = cart.getEmployee();
        Contract contract = new Contract();
        contractRepository.save(contract);
        contract.setTimeStart(cart.getTimeStart());
        contract.setTimeEnd(cart.getTimeEnd());
        contract.setEmployee(employee);
        contract.setCreateAt(LocalDate.now());
        contract.setNameService(cart.getService().getName());
        contract.setAgePatient(cart.getAgePatient());
        contract.setPriceService(cart.getService().getPriceEmployee());
        contract.setFeePrice(cart.getService().getFees());
        contract.setTotalPrice(cart.getService().getTotalPrice());
        LocationPlace locationPlace = new LocationPlace();
        locationPlace.setName(cart.getLocationPlace().getName());
        locationPlace.setLatitude(cart.getLocationPlace().getLatitude());
        locationPlace.setLongitude(cart.getLocationPlace().getLongitude());
        locationPlace.setDistanceForWork(cart.getLocationPlace().getDistanceForWork());
        locationPalaceRepository.save(locationPlace);
        contract.setLocationPlace(locationPlace);
        List<HistoryWorking> historyWorkingList = historyWorkingService.createTest(contract);
        contract.setHistoryWorking(historyWorkingList);
        contract.setFeeContact(BigDecimal.valueOf(200000));
        contract.setFeeAmount(contract.getFeePrice().multiply(BigDecimal.valueOf(historyWorkingList.size())));
        contract.setAmount(contract.getPriceService().multiply(BigDecimal.valueOf(historyWorkingList.size())));
        contract.setTotalAmount(contract.getTotalPrice().multiply(BigDecimal.valueOf(historyWorkingList.size())).add(contract.getFeeContact()));
        contractRepository.save(contract);
        cartService.deleteById(cartId);
        return contract.getId();
    }

    public Contract findById(String id) {
        return contractRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Contract", id)));
    }

    public ContractDetailResponse findDetailContractById(String id) {
        var contract = contractRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "Contract", id)));

        var result = AppUtil.mapper.map(contract, ContractDetailResponse.class);
        Optional<Employee> employee = employeeRepository.findById(contract.getEmployee().getId());
        result.setEmployeeName(employee.get().getFirstName());

        return result;
    }

    public void edit(ContractEditRequest request, String id) {
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

    public Contract create(Contract contract) {
        return contractRepository.save(contract);
    }

    public ContractRevenueContractResponse calculateRevenue(AdminStartEndDayRequest req)  {

        if (req.getEndDay() == "" || req.getEndDay() == null) {
            req.setEndDay("2050-02-20");
        }
        if (req.getStartDay() == "" || req.getStartDay() == null) {
            req.setStartDay("2021-02-20");
        }
        req.setStartDay(AppConvertString.converString(req.getStartDay()));
        req.setEndDay(AppConvertString.converString(req.getEndDay()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = dateFormat.parse(req.getStartDay());
            Date endDate = dateFormat.parse(req.getEndDay());

            if (endDate.before(startDate)) {
                String dateMid = req.getStartDay();
                req.setStartDay(req.getEndDay());
                req.setEndDay(dateMid);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        BigDecimal feeAmountRevenue = contractRepository.getAllFeeAmount(req);
        BigDecimal feeContactRevenue = contractRepository.getAllFeeContact(req);
        if (feeContactRevenue == null){
            feeAmountRevenue = BigDecimal.valueOf(0);
        }if (feeContactRevenue == null){
            feeContactRevenue = BigDecimal.valueOf(0);
        }
        ContractRevenueContractResponse revenue = new ContractRevenueContractResponse(feeAmountRevenue, feeContactRevenue);
        return revenue;
    }
}
