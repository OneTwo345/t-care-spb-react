package cg.tcarespb.repository;

import cg.tcarespb.models.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddInfoRepository extends JpaRepository<EmployeeInfo,String> {
    void deleteAllByEmployeeId(String employee_id);
}
