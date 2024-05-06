package Rudiko.schoollanguages.controller;

import Rudiko.schoollanguages.model.User;
import Rudiko.schoollanguages.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/features")
@CrossOrigin(origins = "http://localhost:3000")
public class FeaturesController {
    private final UserServiceImpl userService;

    @PostMapping("add/{userId}/language-courses/{languageCourseId}")
    public void addLanguageCourseToUser(
            @PathVariable Long userId,
            @PathVariable Long languageCourseId) {
        userService.addLanguageCourseToUser(userId, languageCourseId);
    }

    @DeleteMapping("delete/{userId}/language-courses/{languageCourseId}")
    public ResponseEntity<?> deleteLanguageCourseOfUser(
            @PathVariable Long userId,
            @PathVariable Long languageCourseId){
        return userService.deleteLanguageCourseOfUser(userId, languageCourseId);
    }

}
