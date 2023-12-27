package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.service.serviceGeneral.ServiceGeneralService;
import cg.tcarespb.service.serviceGeneral.request.ServiceSaveRequest;
import cg.tcarespb.service.serviceGeneral.response.ServiceListResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/serviceGenerals")
@AllArgsConstructor
public class ServiceGeneralRestController {
    private final ServiceGeneralService serviceGeneralService;

    @GetMapping
    public ResponseEntity<List<ServiceListResponse>> getAllGeneralService() {
        List<ServiceListResponse> serviceListResponseList = serviceGeneralService.getAll();
        return new ResponseEntity<>(serviceListResponseList, HttpStatus.OK);
    }

    @PostMapping
    public void create(@RequestBody ServiceSaveRequest req) {
        serviceGeneralService.create(req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceGeneral(@PathVariable("id") String id) {
        serviceGeneralService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
