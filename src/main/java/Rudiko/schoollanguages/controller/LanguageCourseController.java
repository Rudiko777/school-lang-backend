package Rudiko.schoollanguages.controller;

import Rudiko.schoollanguages.dtos.FilterCourseDto;
import Rudiko.schoollanguages.exceptions.AppError;
import Rudiko.schoollanguages.model.LanguageCourse;
import Rudiko.schoollanguages.model.Module;
import Rudiko.schoollanguages.model.Review;
import Rudiko.schoollanguages.repository.LanguageCourseRepository;
import Rudiko.schoollanguages.service.LanguageCourseService;
import Rudiko.schoollanguages.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Filter;

@RestController
@RequestMapping("/api/v1/languageCourses")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LanguageCourseController {
    private final LanguageCourseService serviceLanguageCourse;

    @GetMapping
    public List<LanguageCourse> findAllLanguageCourses(){
        return serviceLanguageCourse.findAllLanguageCourses();
    }

    @PostMapping("filter")
    public List<LanguageCourse> filterLangCourses(@RequestBody FilterCourseDto courseDto){
        return serviceLanguageCourse.filterLangCourses(courseDto);
    }

    @PostMapping("save_course")
    public LanguageCourse saveLangCourse(@RequestBody LanguageCourse course){
        return serviceLanguageCourse.saveLangCourse(course);
    }

    @PostMapping("/addModule/{courseId}")
    public ResponseEntity<?> addModuleToCourse(@PathVariable Long courseId, @RequestBody Module module) {
        return serviceLanguageCourse.addModuleToCourse(courseId, module);
    }

    @PostMapping("/addReview/{courseId}")
    public ResponseEntity<?> addReviewToCourse(@PathVariable Long courseId, @RequestBody Review review) {
        return serviceLanguageCourse.addReviewToCourse(courseId, review);
    }


    @GetMapping("/{id}")
    public LanguageCourse findById(@PathVariable("id") Long id){
        return serviceLanguageCourse.findById(id);
    }

    @GetMapping("/find-by-ids/{id}")
    public List<LanguageCourse> findLanguageCoursesByIds(@PathVariable("id") Long id) {
        return serviceLanguageCourse.findLanguageCoursesByIds(id);
    }

    @PutMapping("update_courses")
    public LanguageCourse updateLanguageCourses(@RequestBody LanguageCourse course){
        return serviceLanguageCourse.updateLanguageCourses(course);
    }

    @DeleteMapping("delete_course/{id}")
    public void deleteLanguageCourse(@PathVariable("id") Long id){
        serviceLanguageCourse.deleteLanguageCourse(id);
    }
}