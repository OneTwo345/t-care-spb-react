package cg.tcarespb.repository;

import cg.tcarespb.models.HistoryWorking;
import cg.tcarespb.models.LocationPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryWorkingRepository extends JpaRepository<HistoryWorking, String> {
    void deleteAllByCartId(String cart_id);

    List<HistoryWorking> getAllByEmployeeId(String employeeId);
    List<HistoryWorking> findAllByCartId(String cartId);
}
