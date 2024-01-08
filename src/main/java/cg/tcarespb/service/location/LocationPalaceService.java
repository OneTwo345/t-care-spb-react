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
    public Double getDistance (Double lat1,Double long1,Double lat2,Double long2){
        double earthRadius = 6371000; // Đường kính Trái đất trong mét
        double lat1Radians = Math.toRadians(lat1);
        double lon1Radians = Math.toRadians(long1);
        double lat2Radians = Math.toRadians(lat2);
        double lon2Radians = Math.toRadians(long2);

        double distance = earthRadius * Math.acos(Math.sin(lat1Radians) * Math.sin(lat2Radians) +
                Math.cos(lat1Radians) * Math.cos(lat2Radians) * Math.cos(lon1Radians - lon2Radians));

        distance = distance / 1000; // Chuyển sang kilômét

        return distance;
    }


}
