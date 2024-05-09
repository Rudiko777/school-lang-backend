package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.dtos.JwtRequest;
import Rudiko.schoollanguages.dtos.RegistrationUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    ResponseEntity<?> addUser(@RequestBody RegistrationUserDto registrationUserDto);

    ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest);

    ResponseEntity<?> checkRoles(String token);
}
