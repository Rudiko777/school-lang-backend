package Rudiko.schoollanguages.controller;

import Rudiko.schoollanguages.model.LanguageCourse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/languageCourses")
public class LanguageCourseController {
    @GetMapping
    public List<LanguageCourse> findAllLanguageCourses(){
        return List.of(
                LanguageCourse.builder().language("Немецкий").title("Немецкий для начального уровня").level("Начальный").duration(45).quantityModules(3).price(6520).build(),
                LanguageCourse.builder().language("Испанский").title("Испанский среднего уровня").level("Средний").duration(56).quantityModules(2).price(7890).build(),
                LanguageCourse.builder().language("Китайский").title("Китайский среднего уровня").level("Средний").duration(88).quantityModules(5).price(11250).build()
        );
    }
}