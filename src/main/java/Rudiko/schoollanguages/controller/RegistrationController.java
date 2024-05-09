package Rudiko.schoollanguages.controller;

import Rudiko.schoollanguages.dtos.JwtRequest;
import Rudiko.schoollanguages.dtos.RegistrationUserDto;
import Rudiko.schoollanguages.service.impl.AuthServiceImpl;
import Rudiko.schoollanguages.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/apps")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
    private final AuthServiceImpl authService;
    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to auth approach";
    }

    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@ModelAttribute RegistrationUserDto registrationUserDto) {
        return authService.addUser(registrationUserDto);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        return authService.createAuthToken(authRequest);
    }

    @GetMapping("/roles")
    public List<String> getUserRoles(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        return jwtTokenUtils.getRoles(token);
    }
}