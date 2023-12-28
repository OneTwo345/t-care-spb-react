package cg.tcarespb.service.serviceGeneral;

import cg.tcarespb.models.ServiceGeneral;
import cg.tcarespb.repository.ServiceGeneralRepository;
import cg.tcarespb.service.serviceGeneral.request.ServiceSaveRequest;
import cg.tcarespb.service.serviceGeneral.response.ServiceListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ServiceGeneralService {
    private final ServiceGeneralRepository serviceGeneralRepository;

    public void create(ServiceSaveRequest req) {
        ServiceGeneral serviceGeneral = new ServiceGeneral();
        serviceGeneral.setName(req.getName());
        serviceGeneral.setDescription(req.getDescription());
        serviceGeneralRepository.save(serviceGeneral);
    }

    public void delete(String id) {
        serviceGeneralRepository.deleteById(id);
    }

    public List<ServiceListResponse> getAll() {
        return serviceGeneralRepository.findAll().stream().map(service -> ServiceListResponse.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription()).build()).collect(Collectors.toList());
    }

    public ServiceGeneral findById(String id) {
        return serviceGeneralRepository.findById(id).get();
    }

}