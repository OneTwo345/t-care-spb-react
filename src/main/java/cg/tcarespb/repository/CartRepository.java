package cg.tcarespb.repository;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.enums.EStatus;
import cg.tcarespb.service.cart.response.CartAllFieldResponse;
import cg.tcarespb.service.employee.response.EmployeeListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {
    List<Cart> findAllByUserId(String userId);

    @Query("SELECT NEW  cg.tcarespb.service.cart.response.CartAllFieldResponse(" +
            "c.id," +
            "c.timeStart," +
            "c.timeEnd," +
            "c.agePatient," +
            "c.noteForPatient," +
            "c.noteForEmployee," +
            "c.firstName," +
            "c.lastName," +
            "c.saleNote," +
            "c.phone," +
            "c.memberOfFamily," +
            "c.gender," +
            "c.eDecade" +
            ")  FROM Cart c where  c.user.id =:idUser ")
    Page<CartAllFieldResponse> findAllCartByUserId(@Param("idUser") String idUser, Pageable pageable);

}
