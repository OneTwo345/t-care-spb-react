package cg.tcarespb.service.historyWorking;

import cg.tcarespb.models.*;
import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.repository.CartRepository;
import cg.tcarespb.repository.HistoryWorkingRepository;
import cg.tcarespb.service.historyWorking.response.HistoryWorkingResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class HistoryWorkingService {
    private final HistoryWorkingRepository historyWorkingRepository;
    private final CartRepository cartRepository;

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
        historyWorking.setDateWork(date);
        historyWorking.setEmployee(employee);
        historyWorking.setContract(contract);
        return historyWorkingRepository.save(historyWorking);
    }

    public HistoryWorking createForCart(DateSession dateSession, LocalDate date, Cart cart) {
        HistoryWorking historyWorking = new HistoryWorking();
        historyWorking.setDateInWeek(dateSession.getDateInWeek());
        historyWorking.setSessionOfDate(dateSession.getSessionOfDate());
        historyWorking.setDateWork(date);
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

    public List<HistoryWorking> createHistoryWorkingForCart(List<DateSession> dateSessionList, LocalDate startDate, LocalDate endDate, Cart cart) {
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

    public List<HistoryWorking> createTest(Contract contract,Cart cart) {
        Employee employee = contract.getEmployee();
        LocalDate startDate = contract.getTimeStart();
        LocalDate endDate = contract.getTimeEnd();
        List<DateSession> dateSessionList = cart.getDateSessions();
        List<HistoryWorking> historyWorkingList = createHistoryWorking(dateSessionList, startDate, endDate, employee, contract);
        List<HistoryWorking> historyWorkingEmployee = employee.getHistoryWorking();
        historyWorkingEmployee.addAll(historyWorkingList);
        employee.setHistoryWorking(historyWorkingEmployee);
            return historyWorkingList;
    }

    public List<HistoryWorking> createHistoryWorkingForCart(Cart cart) {
        LocalDate startDate = cart.getTimeStart();
        LocalDate endDate = cart.getTimeEnd();
        List<DateSession> dateSessionList = cart.getDateSessions();
        List<HistoryWorking> historyWorkingList = createHistoryWorkingForCart(dateSessionList, startDate, endDate, cart);
        List<HistoryWorking> historyWorkingEmployee = cart.getHistoryWorking();
        if (historyWorkingEmployee == null) {
            return historyWorkingList;
        } else {
            historyWorkingEmployee.addAll(historyWorkingList);
            cart.setHistoryWorking(historyWorkingEmployee);
            cartRepository.save(cart);
            return historyWorkingEmployee;
        }
    }

    public List<HistoryWorkingResponse> getHistoryWorkingByEmployeeId(String employeeId) {
        return historyWorkingRepository.getAllByEmployeeId(employeeId).stream().map(e -> HistoryWorkingResponse.builder()
                        .dateOfWeek(e.getDateInWeek().getName())
                        .dateSession(e.getSessionOfDate().getName())
                        .date(e.getDateWork().format(DateTimeFormatter.ofPattern("dd/mm/yyyy"))).build())
                .collect(Collectors.toList());
    }
}