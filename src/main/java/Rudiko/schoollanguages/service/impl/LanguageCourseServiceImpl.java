package Rudiko.schoollanguages.service.impl;

import Rudiko.schoollanguages.model.LanguageCourse;
import Rudiko.schoollanguages.repository.LanguageCourseRepository;
import Rudiko.schoollanguages.service.LanguageCourseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class LanguageCourseServiceImpl implements LanguageCourseService{
    private final LanguageCourseRepository repository;

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
}
