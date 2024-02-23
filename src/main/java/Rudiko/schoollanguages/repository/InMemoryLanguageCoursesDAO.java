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

    public LanguageCourse findByTitle(String title) {
        return COURSES.stream()
                .filter(course -> course.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    public LanguageCourse updateLanguageCourses(LanguageCourse course) {
        var courseIndex = IntStream.range(0, COURSES.size())
                .filter(index -> COURSES.get(index).getTitle().equals(course.getTitle()))
                .findFirst()
                .orElse(-1);
        if (courseIndex > -1){
            COURSES.set(courseIndex, course);
            return course;
        }
        return null;
    }

    public void deleteLanguageCourse(String title) {
        var course = findByTitle(title);
        if (course != null) COURSES.remove(course);
    }
}
