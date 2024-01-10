package cg.tcarespb.service.historyWorking;

import cg.tcarespb.models.*;
import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.repository.HistoryWorkingRepository;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.contract.ContractService;
import cg.tcarespb.service.employee.EmployeeService;
import cg.tcarespb.service.historyWorking.request.HistoryWorkingForCartRequest;
import cg.tcarespb.service.historyWorking.request.HistoryWorkingSaveRequest;
import cg.tcarespb.service.historyWorking.response.HistoryWorkingResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HistoryWorkingService {
    private final HistoryWorkingRepository historyWorkingRepository;
    private final EmployeeService employeeService;
    private final ContractService contractService;
    private final CartService cartService;



    public Map<DayOfWeek, LocalDate[]> create(LocalDate startDate, LocalDate endDate) {
        Map<DayOfWeek, LocalDate[]> dayOfWeekMap = new HashMap<>(); // Map để lưu trữ mảng ngày trong tuần cho mỗi ngày
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            LocalDate[] dates = dayOfWeekMap.getOrDefault(dayOfWeek, new LocalDate[0]);
            LocalDate[] updatedDates = Arrays.copyOf(dates, dates.length + 1);
            updatedDates[dates.length] = date;
            dayOfWeekMap.put(dayOfWeek, updatedDates);
            date = date.plusDays(1);
        }
        return dayOfWeekMap;
    }

    public HistoryWorking create(DateSession dateSession, LocalDate date, Employee employee, Contract contract) {
        HistoryWorking historyWorking = new HistoryWorking();
        historyWorking.setDateInWeek(dateSession.getDateInWeek());
        historyWorking.setSessionOfDate(dateSession.getSessionOfDate());
        historyWorking.setDate(date);
        historyWorking.setEmployee(employee);
        historyWorking.setContract(contract);
        return historyWorkingRepository.save(historyWorking);
    }
    public HistoryWorking createForCart(DateSession dateSession, LocalDate date, Cart cart) {
        HistoryWorking historyWorking = new HistoryWorking();
        historyWorking.setDateInWeek(dateSession.getDateInWeek());
        historyWorking.setSessionOfDate(dateSession.getSessionOfDate());
        historyWorking.setDate(date);
        historyWorking.setCart(cart);
        return historyWorkingRepository.save(historyWorking);
    }


    public List<HistoryWorking> createHistoryWorking(List<DateSession> dateSessionList, LocalDate startDate, LocalDate endDate, Employee employee, Contract contract) {
        Map<DayOfWeek, LocalDate[]> dateWork = create(startDate, endDate);
        List<HistoryWorking> historyWorkingList = new ArrayList<>();
        for (DateSession dateSession : dateSessionList) {
            EDateInWeek sessionDateInWeek = dateSession.getDateInWeek();
            LocalDate[] sessionDates = dateWork.get(sessionDateInWeek.convertToDayOfWeek(sessionDateInWeek));
            if (sessionDates != null) {
                for (LocalDate date : sessionDates) {
                    HistoryWorking historyWorking = create(dateSession, date, employee, contract);
                    historyWorkingList.add(historyWorking);
                }
            }
        }
        return historyWorkingList;
    }
    public List<HistoryWorking> createHistoryWorkingForCart(List<DateSession> dateSessionList, LocalDate startDate, LocalDate endDate,Cart cart) {
        Map<DayOfWeek, LocalDate[]> dateWork = create(startDate, endDate);
        List<HistoryWorking> historyWorkingList = new ArrayList<>();
        for (DateSession dateSession : dateSessionList) {
            EDateInWeek sessionDateInWeek = dateSession.getDateInWeek();
            LocalDate[] sessionDates = dateWork.get(sessionDateInWeek.convertToDayOfWeek(sessionDateInWeek));
            if (sessionDates != null) {
                for (LocalDate date : sessionDates) {
                    HistoryWorking historyWorking = createForCart(dateSession, date, cart);
                    historyWorkingList.add(historyWorking);
                }
            }
        }
        return historyWorkingList;
    }
    public List<HistoryWorking> createTest(HistoryWorkingSaveRequest req) {
        Employee employee = employeeService.findById(req.getIdEmployee());
        Contract contract = new Contract();
        contractService.create(contract);
        LocalDate startDate = LocalDate.parse(req.getTimeStart(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse(req.getTimeEnd(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<DateSession> dateSessionList = employee.getDateSessions();
        List<HistoryWorking> historyWorkingList =createHistoryWorking(dateSessionList, startDate, endDate, employee, contract);
        List<HistoryWorking> historyWorkingEmployee = employee.getHistoryWorking();
        historyWorkingEmployee.addAll(historyWorkingList);
        employee.setHistoryWorking(historyWorkingEmployee);
        employeeService.saveEmployee(employee);
        return historyWorkingList ;
    }
    public List<HistoryWorkingResponse> createHistoryWorkingForCart(HistoryWorkingForCartRequest req) {
        Cart cart = cartService.findById(req.getIdCart());
        LocalDate startDate = cart.getTimeStart();
        LocalDate endDate = cart.getTimeEnd();
        List<DateSession> dateSessionList = cart.getDateSessions();
        List<HistoryWorking> historyWorkingList =createHistoryWorkingForCart(dateSessionList, startDate, endDate,cart);
        List<HistoryWorking> historyWorkingEmployee = cart.getHistoryWorking();
        historyWorkingEmployee.addAll(historyWorkingList);
        cart.setHistoryWorking(historyWorkingEmployee);
        cartService.saveCart(cart);
        return historyWorkingList.stream().map(e-> HistoryWorkingResponse.builder()
                .dateOfWeek(e.getDateInWeek().getName())
                .date(e.getDate().toString()).
                dateSession(e.getSessionOfDate().getName()).build()).collect(Collectors.toList());
    }
}