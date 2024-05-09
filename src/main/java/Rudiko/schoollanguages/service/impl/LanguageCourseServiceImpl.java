package Rudiko.schoollanguages.service.impl;

import Rudiko.schoollanguages.dtos.FilterCourseDto;
import Rudiko.schoollanguages.exceptions.AppError;
import Rudiko.schoollanguages.model.LanguageCourse;
import Rudiko.schoollanguages.model.Module;
import Rudiko.schoollanguages.model.Review;
import Rudiko.schoollanguages.repository.LanguageCourseRepository;
import Rudiko.schoollanguages.service.LanguageCourseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Primary
public class LanguageCourseServiceImpl implements LanguageCourseService{
    private final LanguageCourseRepository repository;
    private final UserServiceImpl userService;

    @Override
    public List<LanguageCourse> findAllLanguageCourses() {
        return repository.findAll();
    }

    @Override
    public LanguageCourse saveLangCourse(LanguageCourse course) {
        return repository.save(course);
    }

    @Override
    public LanguageCourse findById(Long id) {
        return repository.findLanguageCourseById(id);
    }

    @Override
    public LanguageCourse updateLanguageCourses(LanguageCourse course) {
        return repository.save(course);
    }

    @Override
    @Transactional
    public void deleteLanguageCourse(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<LanguageCourse> filterLangCourses(FilterCourseDto filterCourseDto) {
        String language = filterCourseDto.getLanguage();
        List<String> levels = filterCourseDto.getLevels();
        List<LanguageCourse> languageCourses = repository.findAll();


        List<LanguageCourse> filteredCourses = new ArrayList<>();

        for (LanguageCourse course : languageCourses) {
            if ((course.getLanguage().equals(language) || Objects.equals(language, "Все")) && levels.contains(course.getLevel())) {
                filteredCourses.add(course);
            }
        }

        return filteredCourses;
    }

    @Override
    public ResponseEntity<?> addModuleToCourse(Long courseId, Module module) {
        LanguageCourse languageCourse = this.findById(courseId);

        if (languageCourse != null) {
            List<Module> modules = languageCourse.getModules();
            modules.add(module);
            languageCourse.setModules(modules);
            repository.save(languageCourse);
            return new ResponseEntity<>("Модуль успешно добавлен курсу", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Неправильно добавлен модуль"), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> addReviewToCourse(Long courseId, Review review) {
        LanguageCourse languageCourse = this.findById(courseId);

        if (languageCourse != null) {
            List<Review> reviews = languageCourse.getReviews();
            reviews.add(review);
            languageCourse.setReviews(reviews);
            repository.save(languageCourse);
            return new ResponseEntity<>("Отзыв успешно добавлен курсу", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Неправильно добавлен отзыв"), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<LanguageCourse> findLanguageCoursesByIds(Long id) {
        List<Long> indexes = userService.findUserById(id).getLanguageCourses();
        List<LanguageCourse> languageCourses = new ArrayList<>();
        for (Long i : indexes) {
            LanguageCourse languageCourse = this.findById(i);
            if (languageCourse != null) {
                languageCourses.add(languageCourse);
            }
        }
        return languageCourses;
    }
}
