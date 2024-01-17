package cg.tcarespb.repository;

import cg.tcarespb.models.User;
import cg.tcarespb.service.admin.response.AdminEmployeeResponse;
import cg.tcarespb.service.admin.response.AdminUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,String> {
    @Query("SELECT NEW  cg.tcarespb.service.admin.response.AdminUserResponse(u.id,u.personID,u.firstName,u.lastName,u.gender)  FROM User u where u.deleted =:deleted ")
    Page<AdminUserResponse> getAllUser(Pageable pageable,@Param("deleted") Boolean deleted);

}
