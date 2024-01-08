package cg.tcarespb.service.location;

import cg.tcarespb.models.LocationPlace;
import cg.tcarespb.repository.LocationPalaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LocationPalaceService {
    private final LocationPalaceRepository locationPalaceRepository;
    public LocationPlace create (LocationPlace locationPalace){
        return locationPalaceRepository.save(locationPalace);
    }


}
