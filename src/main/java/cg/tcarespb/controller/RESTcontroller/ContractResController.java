package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.models.ChatMessage;
import cg.tcarespb.service.contract.ContractService;
import cg.tcarespb.service.contract.request.ContractEditRequest;
import cg.tcarespb.service.contract.request.ContractSaveFromCartRequest;
import cg.tcarespb.service.contract.request.ContractSaveRequest;
import cg.tcarespb.service.contract.response.ContractDetailResponse;
import cg.tcarespb.service.contract.response.ContractListResponse;
import cg.tcarespb.service.contract.response.ContractResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contracts")
@CrossOrigin("http://localhost:3000")
public class ContractResController {
    private final ContractService contractService;
    private final SimpMessageSendingOperations messagingTemplate;

    //    @GetMapping
//    public ResponseEntity<List<ContractListResponse>> getContractList() {
//        List<ContractListResponse> contractListResponses = contractService.getContractList();
//        return ResponseEntity.ok(contractListResponses);
//    }
    @GetMapping
    public ResponseEntity<?> getAllContract(Pageable pageable) {
        Page<ContractResponse> contractListResponses = contractService.getAllContract(pageable);
        return ResponseEntity.ok(contractListResponses);
    }

    @GetMapping("/employees/{idEmployee}")
    public ResponseEntity<?> getContractsByEmployee(@PathVariable("idEmployee") String idEmployee, Pageable pageable) {
        Page<ContractResponse> contractListResponses = contractService.getContractByEmployeeId(idEmployee, pageable);
        return ResponseEntity.ok(contractListResponses);
    }

    @GetMapping("/users/{idUser}")
    public ResponseEntity<?> getContractsByUser(@PathVariable("idUser") String idUser, Pageable pageable) {
        Page<ContractResponse> contractListResponses = contractService.getContractByUserId(idUser, pageable);
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
    public ResponseEntity<?> create(@PathVariable("cartId") String cartId) {
        contractService.createContract(cartId);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage("Sent to User");
        chatMessage.setTimeStamp(new Date());
        messagingTemplate.convertAndSend("/topic/messages", chatMessage);
        return ResponseEntity.noContent().build();
    }

}
