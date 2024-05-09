package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.dtos.FilterCourseDto;
import Rudiko.schoollanguages.model.LanguageCourse;
import Rudiko.schoollanguages.model.Module;
import Rudiko.schoollanguages.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface LanguageCourseService {
    List<LanguageCourse> findAllLanguageCourses();
    LanguageCourse saveLangCourse(LanguageCourse course);
    LanguageCourse findById(Long id);
    LanguageCourse updateLanguageCourses(LanguageCourse course);
    void deleteLanguageCourse(Long id);
    List<LanguageCourse> filterLangCourses(FilterCourseDto filterCourseDto);
    ResponseEntity<?>  addModuleToCourse(Long courseId, Module module);
    ResponseEntity<?> addReviewToCourse(Long courseId, Review review);
    List<LanguageCourse> findLanguageCoursesByIds(Long id);
}
