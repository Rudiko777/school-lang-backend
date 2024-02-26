package Rudiko.schoollanguages.controller;

import Rudiko.schoollanguages.model.LanguageCourse;
import Rudiko.schoollanguages.service.LanguageCourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("save_course")
    public LanguageCourse saveLangCourse(@RequestBody LanguageCourse course){
        return serviceLanguageCourse.saveLangCourse(course);
    }

    @GetMapping("/{id}")
    public LanguageCourse findById(@PathVariable("id") Long id){
        return serviceLanguageCourse.findById(id);
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