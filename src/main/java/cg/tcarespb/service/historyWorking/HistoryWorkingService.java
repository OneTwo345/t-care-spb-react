package cg.tcarespb.service.historyWorking;

import cg.tcarespb.models.Contract;
import cg.tcarespb.models.DateSession;
import cg.tcarespb.models.Employee;
import cg.tcarespb.models.HistoryWorking;
import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.repository.HistoryWorkingRepository;
import cg.tcarespb.service.contract.ContractService;
import cg.tcarespb.service.employee.EmployeeService;
import cg.tcarespb.service.historyWorking.request.HistoryWorkingSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class HistoryWorkingService {
    private final HistoryWorkingRepository historyWorkingRepository;
    private final EmployeeService employeeService;
    private final ContractService contractService;


    public Map<DayOfWeek, LocalDate[]> create(LocalDate startDate, LocalDate endDate) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate timeStart = LocalDate.parse(req.getTimeStart(), dateTimeFormatter);
//        LocalDate timeEnd = LocalDate.parse(req.getTimeEnd(), dateTimeFormatter);
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

    public List<HistoryWorking> createTest(HistoryWorkingSaveRequest req) {
        Employee employee = employeeService.findById(req.getIdEmployee());
//        Contract contract = contractService.findById(req.getIdContract());
        Contract contract = new Contract();
        contractService.create(contract);
        LocalDate startDate = LocalDate.parse(req.getTimeStart(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse(req.getTimeEnd(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<DateSession> dateSessionList = employee.getDateSessions();
        return createHistoryWorking(dateSessionList, startDate, endDate, employee, contract);
    }
}