package Rudiko.schoollanguages.repository;

import Rudiko.schoollanguages.model.LanguageCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageCourseRepository extends JpaRepository<LanguageCourse, Long> {
    void deleteByTitle(String title);
    LanguageCourse findLanguageCourseByTitle(String title);
}
