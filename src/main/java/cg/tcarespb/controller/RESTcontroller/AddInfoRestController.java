package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.models.AddInfo;
import cg.tcarespb.service.addInfo.AddInfoService;
import cg.tcarespb.service.addInfo.request.AddInfoEditRequest;
import cg.tcarespb.service.addInfo.request.AddInfoSaveRequest;
import cg.tcarespb.service.addInfo.response.AddInfoDetailResponse;
import cg.tcarespb.service.addInfo.response.AddInfoListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/add-infos")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AddInfoRestController {
    private final AddInfoService addInfoService;

    @GetMapping
    public ResponseEntity<List<AddInfoListResponse>> getAddInfoListResponse() {
        List<AddInfoListResponse> addInfoListResponseList = addInfoService.getAddInfoList();
        return ResponseEntity.ok(addInfoListResponseList);
    }

    @PostMapping
    public void create(@RequestBody AddInfoSaveRequest request){
        addInfoService.create(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddInfoDetailResponse> findById(@PathVariable("id") String id){
        AddInfoDetailResponse addInfo = addInfoService.findById(id);
        return ResponseEntity.ok(addInfo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> edit(@PathVariable("id") String id, @RequestBody AddInfoEditRequest request) {
        addInfoService.edit(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        addInfoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
