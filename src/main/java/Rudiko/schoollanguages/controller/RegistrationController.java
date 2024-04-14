package Rudiko.schoollanguages.controller;

import Rudiko.schoollanguages.dtos.JwtRequest;
import Rudiko.schoollanguages.dtos.JwtResponse;
import Rudiko.schoollanguages.dtos.RegistrationUserDto;
import Rudiko.schoollanguages.dtos.UserDTO;
import Rudiko.schoollanguages.exceptions.AppError;
import Rudiko.schoollanguages.model.User;
import Rudiko.schoollanguages.service.AuthService;
import Rudiko.schoollanguages.service.UserService;
import Rudiko.schoollanguages.service.impl.UserServiceImpl;
import Rudiko.schoollanguages.utils.JwtTokenUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/apps")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
    private final AuthService authService;

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
}