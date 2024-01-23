package cg.tcarespb.repository;

import cg.tcarespb.models.Employee;

import cg.tcarespb.models.enums.EStatus;
import cg.tcarespb.service.admin.response.AdminEmployeeResponse;
import cg.tcarespb.service.cart.request.CartFilterRequest;
import cg.tcarespb.service.employee.response.EmployeeFilterResponse;
import cg.tcarespb.service.employee.response.EmployeeListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("SELECT new cg.tcarespb.service.employee.response.EmployeeFilterResponse(e.id,e.locationPlace.name,e.firstName,e.lastName,e.bioTitle,e.descriptionAboutMySelf,e.experience,e.locationPlace.longitude,e.locationPlace.latitude)  FROM Employee e JOIN EmployeeSkill es ON e.id = es.employee.id " +
            "JOIN EmployeeServiceGeneral esg ON e.id = esg.employee.id " +
            "JOIN EmployeeInfo ei ON e.id = ei.employee.id " +
            "WHERE check_list_intersection((SELECT GROUP_CONCAT(eskill.skill.id) FROM EmployeeSkill eskill WHERE e.id = eskill.employee.id), :#{#reqFilter.cartSkillIdList}) > 0 " +
            "AND check_list_intersection((SELECT GROUP_CONCAT(eser.service.id) FROM EmployeeServiceGeneral eser WHERE e.id = eser.employee.id), :#{#reqFilter.cartServiceId}) > 0 " +
            "AND check_list_intersection((SELECT GROUP_CONCAT(ein.addInfo.id) FROM EmployeeInfo ein WHERE e.id = ein.employee.id), :#{#reqFilter.cartInfoIdList}) > 0 " +
            "AND count_matching_records(e.id, :#{#reqFilter.cartId})  < 1 " +
            "AND get_distance(e.locationPlace.latitude,e.locationPlace.longitude,:#{#reqFilter.latitude},:#{#reqFilter.longitude})<= :#{#reqFilter.distance}  " +
            "AND  e.status =:#{#reqFilter.status}  GROUP BY e.id ")
    Page<EmployeeFilterResponse> filter(@Param("reqFilter") CartFilterRequest reqFilter, Pageable pageable);

    @Query("SELECT new cg.tcarespb.service.employee.response.EmployeeFilterResponse(e.id,e.locationPlace.name,e.firstName,e.lastName,e.bioTitle,e.descriptionAboutMySelf,e.experience,e.locationPlace.longitude,e.locationPlace.latitude)  FROM Employee e JOIN EmployeeSkill es ON e.id = es.employee.id " +
            "JOIN EmployeeServiceGeneral esg ON e.id = esg.employee.id " +
            "JOIN EmployeeInfo ei ON e.id = ei.employee.id " +
            "WHERE check_list_intersection((SELECT GROUP_CONCAT(eskill.skill.id) FROM EmployeeSkill eskill WHERE e.id = eskill.employee.id), :#{#reqFilter.cartSkillIdList}) > 0 " +
//            "AND check_list_intersection((SELECT GROUP_CONCAT(eser.service.id) FROM EmployeeServiceGeneral eser WHERE e.id = eser.employee.id), :#{#reqFilter.cartServiceId}) > 0 " +
//            "AND check_list_intersection((SELECT GROUP_CONCAT(ein.addInfo.id) FROM EmployeeInfo ein WHERE e.id = ein.employee.id), :#{#reqFilter.cartInfoIdList}) > 0 "+
//            "AND count_matching_records(e.id, :#{#reqFilter.cartId}) = 0 "+
//            "AND get_distance(e.locationPlace.latitude,e.locationPlace.longitude,:#{#reqFilter.latitude},:#{#reqFilter.longitude})<= :#{#reqFilter.distance}  "+
            "AND  e.status =:#{#reqFilter.status}  GROUP BY e.id ")
    Page<EmployeeFilterResponse> filterTestCase(@Param("reqFilter") CartFilterRequest reqFilter, Pageable pageable);

    @Query("SELECT NEW  cg.tcarespb.service.admin.response.AdminEmployeeResponse(e.id,e.personID,e.firstName,e.lastName,e.gender,e.status)  FROM Employee e where e.deleted=:deleted ")
    Page<AdminEmployeeResponse> getAllEmployee(Pageable pageable, @Param("deleted") Boolean deleted);

    @Query("SELECT NEW  cg.tcarespb.service.admin.response.AdminEmployeeResponse(e.id,e.personID,e.firstName,e.lastName,e.gender,e.status)  FROM Employee e where  e.status =:status ")
    Page<AdminEmployeeResponse> getAllEmployeeByStatus(@Param("status") EStatus status, Pageable pageable);

    @Query("SELECT NEW  cg.tcarespb.service.employee.response.EmployeeListResponse(e.id,e.firstName," +
            "e.lastName," +
            "e.descriptionAboutMySelf," +
            "e.bioTitle," +
            "e.personID," +
            "e.gender," +
            "e.status," +
            "e.education," +
            "e.locationPlace.longitude," +
            "e.locationPlace.latitude," +
            "e.locationPlace.name," +
            "e.photo.url)  FROM Employee e where  e.status =:status ")
    Page<EmployeeListResponse> findAllByStatus(@Param("status")EStatus status, Pageable pageable);
}
