package Rudiko.schoollanguages.repository;

import Rudiko.schoollanguages.model.LanguageCourse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryLanguageCoursesDAO{
    private final List<LanguageCourse> COURSES = new ArrayList<LanguageCourse>();

    public List<LanguageCourse> findAllLanguageCourses() {
        return COURSES;
    }

    public LanguageCourse saveLangCourse(LanguageCourse course) {
        COURSES.add(course);
        return course;
    }

    public LanguageCourse findById(Long id) {
        return COURSES.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public LanguageCourse updateLanguageCourses(LanguageCourse course) {
        var courseIndex = IntStream.range(0, COURSES.size())
                .filter(index -> COURSES.get(index).getId().equals(course.getId()))
                .findFirst()
                .orElse(-1);
        if (courseIndex > -1){
            COURSES.set(courseIndex, course);
            return course;
        }
        return null;
    }

    public void deleteLanguageCourse(Long id) {
        var course = findById(id);
        if (course != null) COURSES.remove(course);
    }
}
