package cg.tcarespb.service.addInfo;

import cg.tcarespb.models.AddInfo;
import cg.tcarespb.repository.AddInfoRepository;
import cg.tcarespb.service.addInfo.request.AddInfoEditRequest;
import cg.tcarespb.service.addInfo.request.AddInfoSaveRequest;
import cg.tcarespb.service.addInfo.response.AddInfoDetailResponse;
import cg.tcarespb.service.addInfo.response.AddInfoListResponse;
import cg.tcarespb.util.AppMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AddInfoService {
    private final AddInfoRepository addInfoRepository;


    public List<AddInfoListResponse> getAddInfoList(){
        return addInfoRepository.findAll()
                .stream()
                .map(service -> AddInfoListResponse.builder()
                        .id(service.getId())
                        .name(service.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public void create(AddInfoSaveRequest request){
        AddInfo addInfo = new AddInfo();
        addInfo.setName(request.getName());
        addInfoRepository.save(addInfo);
    }

    public AddInfo findByIdForEdit(String id) {
        return addInfoRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "AddInfo", id)));
    }

    public AddInfoDetailResponse findById(String id) {
        AddInfo addInfo = addInfoRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "AddInfo", id)));
        return new AddInfoDetailResponse(addInfo.getName());
    }

    public void delete(String id){
        addInfoRepository.deleteById(id);
    }

    public void edit(String id, AddInfoEditRequest request){
        AddInfo addInfo = findByIdForEdit(id);
        addInfo.setName(request.getName());
        addInfoRepository.save(addInfo);
    }






}
