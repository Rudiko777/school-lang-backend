package Rudiko.schoollanguages.controller;

import Rudiko.schoollanguages.model.LanguageCourse;
import Rudiko.schoollanguages.model.User;
import Rudiko.schoollanguages.service.LanguageCourseService;
import Rudiko.schoollanguages.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/languageCourses")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LanguageCourseController {
    private final LanguageCourseService serviceLanguageCourse;
    private final UserServiceImpl userService;

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

    @GetMapping("/find-by-ids/{id}")
    public List<LanguageCourse> findLanguageCoursesByIds(@PathVariable("id") Long id) {
        List<Long> indexes = userService.findUserById(id).getLanguageCourses();
        List<LanguageCourse> languageCourses = new ArrayList<>();
        for (Long i : indexes) {
            LanguageCourse languageCourse = serviceLanguageCourse.findById(i);
            if (languageCourse != null) {
                languageCourses.add(languageCourse);
            }
        }
        return languageCourses;
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