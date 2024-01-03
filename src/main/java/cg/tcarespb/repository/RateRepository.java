package cg.tcarespb.repository;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.Employee;
import cg.tcarespb.models.Rate;
import cg.tcarespb.service.employee.response.EmployeeListResponse;
import cg.tcarespb.service.employee.response.EmployeeListTop3Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate,String> {
    @Query( value =
            "SELECT r.id " +
            "FROM rates r " +
            "WHERE r.rate_quantity >= (SELECT AVG(rate.rate_quantity) " +
            "FROM rates rate)" +
                    " ORDER BY r.star_quantity DESC LIMIT 3",nativeQuery = true)
    List<String> findTop3EmployeesWithHighestRate();
}
