package cg.tcarespb.repository;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.Employee;
import cg.tcarespb.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate,String> {
    @Query("SELECT r.employee FROM Rate r WHERE r.rateQuantity > (SELECT AVG(rate.rateQuantity) FROM Rate rate) ORDER BY r.starQuantity DESC LIMIT 3")
    List<Employee> findTop3EmployeesWithHighestRate();
}
