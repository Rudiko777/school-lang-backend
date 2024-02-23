package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.model.LanguageCourse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LanguageCourseService {
    List<LanguageCourse> findAllLanguageCourses();
    LanguageCourse saveLangCourse(LanguageCourse course);
    LanguageCourse findByTitle(String title);
    LanguageCourse updateLanguageCourses(LanguageCourse course);
    void deleteLanguageCourse(String title);
}
