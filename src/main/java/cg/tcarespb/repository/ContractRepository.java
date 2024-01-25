package cg.tcarespb.repository;

import cg.tcarespb.models.Contract;
import cg.tcarespb.models.enums.EStatus;
import cg.tcarespb.service.admin.request.AdminStartEndDayRequest;
import cg.tcarespb.service.contract.response.ContractResponse;
import cg.tcarespb.service.employee.response.EmployeeListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ContractRepository extends JpaRepository<Contract,String> {
    @Query("SELECT calculate_total_fee_amount_contract(:#{#req.startDay},:#{#req.endDay}) ")
    BigDecimal getAllFeeAmount(AdminStartEndDayRequest req);
    @Query("SELECT calculate_total_fee_contact_contract(:#{#req.startDay},:#{#req.endDay}) ")
    BigDecimal getAllFeeContact(AdminStartEndDayRequest req);
    @Query("SELECT NEW  cg.tcarespb.service.contract.response.ContractResponse(c.id,c.timeStart," +
            "c.timeEnd," +
            "c.createAt," +
            "c.namePatient," +
            "c.content," +
            "c.noteForPatient," +
            "c.noteForEmployee," +
            "c.nameService," +
            "c.feeContact," +
            "c.feePrice," +
            "c.totalPrice," +
            "c.priceService," +
            "c.totalAmount," +
            "c.amount," +
            "c.feeAmount," +
            "c.gender)  FROM Contract c where  c.employee.id =:idEmployee ")
    Page<ContractResponse> findAllByEmployee(@Param("idEmployee") String idEmployee, Pageable pageable);
    @Query("SELECT NEW  cg.tcarespb.service.contract.response.ContractResponse(c.id,c.timeStart," +
            "c.timeEnd," +
            "c.createAt," +
            "c.namePatient," +
            "c.content," +
            "c.noteForPatient," +
            "c.noteForEmployee," +
            "c.nameService," +
            "c.feeContact," +
            "c.feePrice," +
            "c.totalPrice," +
            "c.priceService," +
            "c.totalAmount," +
            "c.amount," +
            "c.feeAmount," +
            "c.gender)  FROM Contract c where  c.user.id =:idUser ")
    Page<ContractResponse> findAllByUser(@Param("idUser") String idUser, Pageable pageable);
    @Query("SELECT NEW  cg.tcarespb.service.contract.response.ContractResponse(c.id,c.timeStart," +
            "c.timeEnd," +
            "c.createAt," +
            "c.namePatient," +
            "c.content," +
            "c.noteForPatient," +
            "c.noteForEmployee," +
            "c.nameService," +
            "c.feeContact," +
            "c.feePrice," +
            "c.totalPrice," +
            "c.priceService," +
            "c.totalAmount," +
            "c.amount," +
            "c.feeAmount," +
            "c.gender)  FROM Contract c")
    Page<ContractResponse> findAllContract(Pageable pageable);
}
