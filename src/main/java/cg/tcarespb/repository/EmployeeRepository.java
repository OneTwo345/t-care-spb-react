package cg.tcarespb.repository;

import cg.tcarespb.models.Employee;
import cg.tcarespb.models.Schedule;
import cg.tcarespb.service.cart.request.CartLocationFilterRequest;
import cg.tcarespb.service.cart.request.CartSkillFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee,String> {
    @Query("SELECT e.id FROM Employee AS e " +
            "where  get_distance(e.locationPlace.latitude,e.locationPlace.longitude,:#{#reqFilter.latitude},:#{#reqFilter.longitude})<= :#{#reqFilter.distance} ")
    Page<String> filterLocation(@Param("reqFilter") CartLocationFilterRequest reqFilter, Pageable pageable);
    @Query("SELECT e.id FROM Employee e JOIN EmployeeSkill es ON e.id = es.employee.id " +
            "WHERE  check_list_intersection((SELECT GROUP_CONCAT(eskill.skill.id) FROM EmployeeSkill eskill WHERE e.id = eskill.employee.id), :#{#reqFilter.cartSkillIdList}) > 0")
    Page<String> filterSkill(@Param("reqFilter") CartSkillFilterRequest reqFilter, Pageable pageable);
}
