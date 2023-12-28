package cg.tcarespb.repository;

import cg.tcarespb.models.Availability;
import cg.tcarespb.models.ServiceGeneral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository  extends JpaRepository<Availability,String> {
}
