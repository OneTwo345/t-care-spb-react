package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.service.cart.response.CartListResponse;
import cg.tcarespb.service.employee.response.EmployeeDetailResponse;
import cg.tcarespb.service.employee.response.EmployeeListTop3Response;
import cg.tcarespb.service.rate.RateService;
import cg.tcarespb.service.rate.request.RateSaveRequest;
import cg.tcarespb.service.rate.response.RateListResponse;
import cg.tcarespb.service.saler.SalerService;
import cg.tcarespb.service.saler.request.SalerSaveRequest;
import cg.tcarespb.service.saler.response.SalerListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salers")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SalerRestController {
    private final SalerService salerService;
    @GetMapping()
    public ResponseEntity<List<SalerListResponse>> getSalerListResponse() {
        List<SalerListResponse> salerListResponses = salerService.getSalerListResponse();
        return ResponseEntity.ok(salerListResponses);
    }

    @PostMapping
    public void create(@RequestBody SalerSaveRequest request){
        salerService.create(request);
    }


}
