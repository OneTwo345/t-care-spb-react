package cg.tcarespb.repository;

import cg.tcarespb.models.Contract;
import cg.tcarespb.service.admin.request.AdminStartEndDayRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ContractRepository extends JpaRepository<Contract,String> {
    @Query("SELECT calculate_total_revenue_contract(:#{#req.startDay},:#{#req.endDay}) ")
    BigDecimal getAllRevenue(AdminStartEndDayRequest req);

}
