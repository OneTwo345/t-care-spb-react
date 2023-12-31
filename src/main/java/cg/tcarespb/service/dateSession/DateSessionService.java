package cg.tcarespb.service.dateSession;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.DateSession;
import cg.tcarespb.models.Employee;
import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.models.enums.ESessionOfDate;
import cg.tcarespb.repository.DateSessionRepository;
import cg.tcarespb.repository.EmployeeRepository;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.dateSession.request.DateSessionListSaveRequest;
import cg.tcarespb.service.dateSession.request.DateSessionSaveRequestForEmployee;
import cg.tcarespb.service.dateSession.response.DateSessionListResponseForEmployee;
import cg.tcarespb.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DateSessionService {
    private final DateSessionRepository dateSessionRepository;
    private final EmployeeRepository employeeRepository;
    private final CartService cartService;

    public DateSession create(DateSession dateSession) {
        return dateSessionRepository.save(dateSession);
    }
        public void updateDateSessionCart(DateSessionListSaveRequest req,String idCart){
                Cart cart = cartService.findById(idCart);
                for (var dateSession: req.getListDateSession()){
                    EDateInWeek date = EDateInWeek.valueOf(dateSession.getDate());
                    for (var sessionOfDate :dateSession.getSessionOfDateList()){
                        ESessionOfDate sessionDate = ESessionOfDate.valueOf(sessionOfDate);
                        DateSession newDateSession = new DateSession();
                        newDateSession.setSessionOfDate(sessionDate);
                        newDateSession.setDateInWeek(date);
                        newDateSession.setCart(cart);
                        List<DateSession> dateSessionList = new ArrayList<>();
                        cart.getDateSessions().add(newDateSession);
                        dateSessionRepository.save(newDateSession);
                    }
                }

        }

        public List<DateSessionListResponseForEmployee> getDateSessionListResponseForEmployee(){
            return dateSessionRepository.findAll()
                    .stream()
                    .map(service -> DateSessionListResponseForEmployee.builder()
                            .id(service.getId())
                            .sessionOfDate(service.getSessionOfDate())
                            .dateInWeek(service.getDateInWeek())
                            .build())
                    .collect(Collectors.toList());
        }

        public void createDateSessionForEmployee(DateSessionSaveRequestForEmployee request){
            var dateSession = AppUtil.mapper.map(request,DateSession.class);
            Optional<Employee> employee = employeeRepository.findById(request.getEmployeeId());
            dateSession.setEmployee(employee.get());
            dateSession = dateSessionRepository.save(dateSession);
        }

}
