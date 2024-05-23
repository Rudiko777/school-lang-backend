package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.model.User;
import Rudiko.schoollanguages.repository.UserRepository;
import Rudiko.schoollanguages.service.impl.UserServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Before("")
    public void setup() {
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    @Test
    public void getCurrentUser_shouldReturnCurrentUser() {
        Authentication authentication = mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn("John Doe");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User expectedUser = new User();
        expectedUser.setFullName("John Doe");
        Mockito.when(userRepository.findByFullName("John Doe")).thenReturn(expectedUser);
        User currentUser = userService.getCurrentUser();
        Assertions.assertEquals(expectedUser, currentUser);
    }

    @Test
    public void testAddLanguageCourseToUser() {
        Long userId = 1L;
        Long languageCourseId = 2L;
        User user = new User();
        user.setId(userId);
        user.setLanguageCourses(new ArrayList<>());

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);
        userService.addLanguageCourseToUser(userId, languageCourseId);
        Assertions.assertTrue(user.getLanguageCourses().contains(languageCourseId));
    }

    @Test
    public void testDeleteLanguageCourseOfUser() {
        Long userId = 1L;
        Long languageCourseId = 2L;
        User user = new User();
        user.setId(userId);
        List<Long> languageCourses = new ArrayList<>();
        languageCourses.add(languageCourseId);
        user.setLanguageCourses(languageCourses);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);
        ResponseEntity<?> response = userService.deleteLanguageCourseOfUser(userId, languageCourseId);
        Assertions.assertFalse(user.getLanguageCourses().contains(languageCourseId));
        Assertions.assertEquals(ResponseEntity.ok().body("Response Body"), response);
    }
}
