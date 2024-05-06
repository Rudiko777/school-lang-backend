package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.dtos.RegistrationUserDto;
import Rudiko.schoollanguages.model.LanguageCourse;
import Rudiko.schoollanguages.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findUserById(Long userId);
    List<User> allUsers();
    User saveUser(RegistrationUserDto registrationUserDto);
    boolean deleteUser(Long userId);
    List<User> userGetList(Long idMin);
    User findByFullName(String fullName);

    User getCurrentUser();

    void addLanguageCourseToUser(Long userId, Long languageCourseId);
    ResponseEntity<?> deleteLanguageCourseOfUser(Long userId, Long languageCourseId);
}
