package Rudiko.schoollanguages.controller;

import Rudiko.schoollanguages.model.User;
import Rudiko.schoollanguages.service.UserService;
import Rudiko.schoollanguages.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;

    @GetMapping("/userList")
    public List<User> userList() {
        return userService.allUsers();
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam(value = "id", required = false) Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User successfully deleted");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
        }
    }

    @GetMapping("get/{userId}")
    public User  getUser(@PathVariable("userId") Long userId) {
        return userService.findUserById(userId);
    }
}
