package cg.tcarespb.repository;

import cg.tcarespb.models.Employee;
import cg.tcarespb.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,String> {

}
