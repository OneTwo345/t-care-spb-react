package cg.tcarespb.repository;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.enums.ECartStatus;
import cg.tcarespb.models.enums.EStatus;
import cg.tcarespb.service.cart.request.CartSearchFilterRequest;
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
    @Query("SELECT NEW  cg.tcarespb.service.cart.response.CartAllFieldResponse(" +
            "c.id," +
            "c.timeStart," +
            "c.timeEnd," +
            "c.noteForPatient," +
            "c.noteForEmployee," +
            "c.firstName," +
            "c.lastName," +
            "c.saleNote," +
            "c.phone," +
            "c.memberOfFamily," +
            "c.gender," +
            "c.eDecade" +
            ")  FROM Cart c where  c.cartStatus =:status ")
    Page<CartAllFieldResponse> findAllCartByCartStatus(@Param("status") ECartStatus status, Pageable pageable);
    @Query("SELECT NEW  cg.tcarespb.service.cart.response.CartAllFieldResponse(" +
            "c.id," +
            "c.timeStart," +
            "c.timeEnd," +
            "c.noteForPatient," +
            "c.noteForEmployee," +
            "c.firstName," +
            "c.lastName," +
            "c.saleNote," +
            "c.phone," +
            "c.memberOfFamily," +
            "c.gender," +
            "c.eDecade" +
            ")  FROM Cart c where  c.cartStatus =:status and (c.user.lastName LIKE CONCAT('%', :#{#req.search},'%') " +
            "or c.user.firstName  LIKE CONCAT('%', :#{#req.search},'%') " +
            "or c.firstName LIKE CONCAT('%', :#{#req.search},'%') " +
            "or c.lastName LIKE CONCAT('%', :#{#req.search},'%') " +
            "or c.employee.firstName LIKE CONCAT('%', :#{#req.search},'%') " +
            "or c.employee.lastName LIKE CONCAT('%', :#{#req.search},'%') " +
            "or c.phone LIKE CONCAT('%', :#{#req.search},'%') " +
            "or c.service.name LIKE CONCAT('%', :#{#req.search},'%'))" )
    Page<CartAllFieldResponse> findAllCartByCartStatusAndSearch(@Param("status") ECartStatus status, CartSearchFilterRequest req, Pageable pageable);
}
