package cg.tcarespb.repository;

import cg.tcarespb.models.DateSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateSessionRepository  extends JpaRepository<DateSession,String> {
    void deleteAllByEmployeeId(String employee_id);
    void deleteAllByCartId(String cart_id);


    List<DateSession> findAllByCartId(String cartId);
}
