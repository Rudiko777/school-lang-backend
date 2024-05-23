package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.dtos.FilterCourseDto;
import Rudiko.schoollanguages.model.LanguageCourse;
import Rudiko.schoollanguages.model.Module;
import Rudiko.schoollanguages.model.Review;
import Rudiko.schoollanguages.repository.LanguageCourseRepository;
import Rudiko.schoollanguages.service.impl.LanguageCourseServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LanguageCourseServiceTest {
    @Mock
    private LanguageCourseRepository repository;

    @InjectMocks
    private LanguageCourseServiceImpl service;

    @Test
    public void testFilterLangCourses() {
        FilterCourseDto filterCourseDto = new FilterCourseDto();
        filterCourseDto.setLanguage("Немецкий");
        filterCourseDto.setLevels(List.of("Средний"));


        List<LanguageCourse> courses = Arrays.asList(
                new LanguageCourse(152L, "Немецкий", "Средний"),
                new LanguageCourse(154L, "Испанский", "Начальынй"),
                new LanguageCourse(153L, "Китайский", "Начальный")
        );

        Mockito.when(repository.findAll()).thenReturn(courses);
        List<LanguageCourse> result = service.filterLangCourses(filterCourseDto);
        List<LanguageCourse> expectedResult = Arrays.asList(courses.get(0));
        Assertions.assertEquals(expectedResult.get(0).getLevel(), result.get(0).getLevel());
    }

    @Test
    public void testAddModuleToCourse() {
        LanguageCourse course = new LanguageCourse(153L, "Китайский", "Начальный");
        course.setModules(new ArrayList<>()); // Initialize the modules list

        Module module = new Module("chineseModule", "desc", 1L);
        Mockito.when(service.findById(153L)).thenReturn(course);
        Mockito.when(repository.save(course)).thenReturn(course);
        ResponseEntity<?> result = service.addModuleToCourse(153L, module);
        Mockito.verify(repository, Mockito.times(1)).save(course);
        ResponseEntity<?> expectedResult = new ResponseEntity<>("Модуль успешно добавлен курсу", HttpStatus.OK);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testAddReviewToCourse() {
        LanguageCourse course = new LanguageCourse(1L, "Немецкий", "Начальный");
        List<Review> reviews = new ArrayList<>();
        course.setReviews(reviews);
        Review review = new Review("Степана", "Хороший курс", 10L, "Ок");

        Mockito.when(service.findById(1L)).thenReturn(course);
        Mockito.when(repository.save(course)).thenReturn(course);
        ResponseEntity<?> result = service.addReviewToCourse(1L, review);
        Mockito.verify(repository, Mockito.times(1)).save(course);
        ResponseEntity<?> expectedResult = new ResponseEntity<>("Отзыв успешно добавлен курсу", HttpStatus.OK);
        Assertions.assertEquals(expectedResult, result);
        Assertions.assertEquals(1, course.getReviews().size());
        Assertions.assertEquals(review, course.getReviews().get(0));
    }
}
