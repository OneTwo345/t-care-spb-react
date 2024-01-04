package cg.tcarespb.repository;

import cg.tcarespb.models.Cart;
import cg.tcarespb.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract,String> {

}
