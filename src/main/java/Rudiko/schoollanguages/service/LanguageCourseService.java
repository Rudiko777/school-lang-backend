package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.model.LanguageCourse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LanguageCourseService {
    List<LanguageCourse> findAllLanguageCourses();
    LanguageCourse saveLangCourse(LanguageCourse course);
    LanguageCourse findById(Long id);
    LanguageCourse updateLanguageCourses(LanguageCourse course);
    void deleteLanguageCourse(Long id);
}
