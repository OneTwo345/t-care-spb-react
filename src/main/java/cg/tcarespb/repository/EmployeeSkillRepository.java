package cg.tcarespb.repository;

import cg.tcarespb.models.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill,String> {
    void deleteAllByEmployeeId(String employee_id);

}
