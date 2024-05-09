package Rudiko.schoollanguages.repository;

import Rudiko.schoollanguages.model.BestStudents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestStudentsRepository extends JpaRepository<BestStudents, Long> {
    BestStudents findByFullName(String fullName);
}
