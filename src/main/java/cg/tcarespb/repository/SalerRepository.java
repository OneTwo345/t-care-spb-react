package cg.tcarespb.repository;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.Saler;
import cg.tcarespb.models.enums.EStatus;
import cg.tcarespb.service.admin.response.AdminEmployeeResponse;
import cg.tcarespb.service.admin.response.AdminSalerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalerRepository extends JpaRepository<Saler,String> {

    @Query("SELECT NEW  cg.tcarespb.service.admin.response.AdminSalerResponse(s.id,s.personID,s.firstName,s.lastName,s.gender)  FROM Saler s where  s.deleted =:deleted ")
    Page<AdminSalerResponse> getAllSaler(@Param("deleted") Boolean deleted, Pageable pageable);
}
