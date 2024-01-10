package cg.tcarespb.repository;

import cg.tcarespb.models.EmployeeServiceGeneral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeServiceGeneralRepository extends JpaRepository<EmployeeServiceGeneral,String> {
void deleteAllByEmployeeId(String employee_id);
}
