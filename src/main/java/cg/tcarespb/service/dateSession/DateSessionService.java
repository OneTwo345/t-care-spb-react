package cg.tcarespb.service.dateSession;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.DateSession;
import cg.tcarespb.models.enums.EDateInWeek;
import cg.tcarespb.models.enums.ESessionOfDate;
import cg.tcarespb.repository.DateSessionRepository;
import cg.tcarespb.service.cart.CartService;
import cg.tcarespb.service.dateSession.request.DateSessionListSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DateSessionService {
    private final DateSessionRepository dateSessionRepository;
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
}
