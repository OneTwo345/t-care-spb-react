package cg.tcarespb.service.dateSession;

import cg.tcarespb.models.DateSession;
import cg.tcarespb.repository.DateSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DateSessionService {
    private final DateSessionRepository dateSessionRepository;

    public DateSession create(DateSession dateSession) {
        return dateSessionRepository.save(dateSession);
    }

}
