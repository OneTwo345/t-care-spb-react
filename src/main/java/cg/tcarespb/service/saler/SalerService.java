package cg.tcarespb.service.saler;

import cg.tcarespb.models.Employee;
import cg.tcarespb.models.Rate;
import cg.tcarespb.models.Saler;
import cg.tcarespb.models.User;
import cg.tcarespb.repository.SalerRepository;
import cg.tcarespb.service.rate.request.RateSaveRequest;
import cg.tcarespb.service.rate.response.RateListResponse;
import cg.tcarespb.service.saler.request.SalerSaveRequest;
import cg.tcarespb.service.saler.response.SalerListResponse;
import cg.tcarespb.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SalerService {
    private final SalerRepository salerRepository;

    public List<SalerListResponse> getSalerListResponse(){
        return salerRepository.findAll()
                .stream()
                .map(service ->SalerListResponse.builder()
                        .id(service.getId())
                        .firstName(service.getFirstName())
                        .lastName(service.getLastName())
                        .gender(String.valueOf(service.getGender()))
                        .personID(service.getPersonID())
                        .build())
                .collect(Collectors.toList());
    }
    public void create(SalerSaveRequest request){
        var saler = AppUtil.mapper.map(request, Saler.class);
        salerRepository.save(saler);
    }

}
