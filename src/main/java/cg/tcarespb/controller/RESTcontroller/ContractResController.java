package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.service.contract.ContractService;
import cg.tcarespb.service.contract.request.ContractEditRequest;
import cg.tcarespb.service.contract.request.ContractSaveFromCartRequest;
import cg.tcarespb.service.contract.request.ContractSaveRequest;
import cg.tcarespb.service.contract.response.ContractDetailResponse;
import cg.tcarespb.service.contract.response.ContractListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contracts")
@CrossOrigin("http://localhost:3000")
public class ContractResController {
    private final ContractService contractService;

    @GetMapping
    public ResponseEntity<List<ContractListResponse>> getContractList() {
        List<ContractListResponse> contractListResponses = contractService.getContractList();
        return ResponseEntity.ok(contractListResponses);
    }

    @PostMapping
    public void create(@RequestBody ContractSaveRequest request) {
        contractService.create(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDetailResponse> getContractDetail(@PathVariable("id") String id) {
        ContractDetailResponse contract = contractService.findDetailContractById(id);
        return ResponseEntity.ok(contract);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> edit(@PathVariable("id") String id, @RequestBody ContractEditRequest request) {
        contractService.edit(request, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/createContract/{cartId}")
    public ResponseEntity<?> create(@PathVariable("cartId")String cartId) {
        contractService.createContract(cartId);
        return ResponseEntity.noContent().build();
    }

}
