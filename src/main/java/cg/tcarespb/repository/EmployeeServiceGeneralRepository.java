package cg.tcarespb.repository;

import cg.tcarespb.models.EmployeeServiceGeneral;
import cg.tcarespb.service.employee.EmployeeService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeServiceGeneralRepository extends JpaRepository<EmployeeServiceGeneral,String> {

}
