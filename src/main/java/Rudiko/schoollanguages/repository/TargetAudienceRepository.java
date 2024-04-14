package Rudiko.schoollanguages.repository;

import Rudiko.schoollanguages.model.TargetAudience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TargetAudienceRepository extends JpaRepository<TargetAudience, Long> {
    List<TargetAudience> findByWhom(String whom);
}
