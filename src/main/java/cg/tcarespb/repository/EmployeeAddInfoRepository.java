package cg.tcarespb.repository;

import cg.tcarespb.models.EmployeeInfo;
import cg.tcarespb.models.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddInfoRepository extends JpaRepository<EmployeeInfo,String> {

}
