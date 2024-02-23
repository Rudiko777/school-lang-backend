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

    @GetMapping("/{title}")
    public LanguageCourse findByTitle(@PathVariable("title") String title){
        return serviceLanguageCourse.findByTitle(title);
    }

    @PutMapping("update_courses")
    public LanguageCourse updateLanguageCourses(@RequestBody LanguageCourse course){
        return serviceLanguageCourse.updateLanguageCourses(course);
    }

    @DeleteMapping("delete_course/{title}")
    public void deleteLanguageCourse(@PathVariable("title") String title){
        serviceLanguageCourse.deleteLanguageCourse(title);
    }
}