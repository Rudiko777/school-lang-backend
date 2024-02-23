package Rudiko.schoollanguages.service.impl;

import Rudiko.schoollanguages.model.LanguageCourse;
import Rudiko.schoollanguages.repository.InMemoryLanguageCoursesDAO;
import Rudiko.schoollanguages.service.LanguageCourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryLanguageCourseServiceImpl implements LanguageCourseService {
    private final InMemoryLanguageCoursesDAO repository;

    @Override
    public List<LanguageCourse> findAllLanguageCourses() {
        return repository.findAllLanguageCourses();
    }

    @Override
    public LanguageCourse saveLangCourse(LanguageCourse course) {
        return repository.saveLangCourse(course);
    }

    @Override
    public LanguageCourse findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public LanguageCourse updateLanguageCourses(LanguageCourse course) {
        return repository.updateLanguageCourses(course);
    }

    @Override
    public void deleteLanguageCourse(String title) {
        repository.deleteLanguageCourse(title);
    }
}