package cg.tcarespb.repository;

import cg.tcarespb.models.Schedule;
import cg.tcarespb.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,String> {

}
