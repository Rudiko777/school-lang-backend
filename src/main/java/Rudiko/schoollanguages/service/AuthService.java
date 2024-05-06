package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.dtos.JwtRequest;
import Rudiko.schoollanguages.dtos.JwtResponse;
import Rudiko.schoollanguages.dtos.RegistrationUserDto;
import Rudiko.schoollanguages.dtos.UserDTO;
import Rudiko.schoollanguages.exceptions.AppError;
import Rudiko.schoollanguages.model.User;
import Rudiko.schoollanguages.service.impl.UserServiceImpl;
import Rudiko.schoollanguages.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService{
    private final UserServiceImpl userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> addUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByFullName(registrationUserDto.getFullName()) != null){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.saveUser(registrationUserDto);
        return ResponseEntity.ok(new UserDTO(
                user.getFullName(),
                user.getPassword(),
                user.getPasswordConfirm(),
                user.getPhoneNumber(),
                user.getBirthday(),
                user.getGender(),
                user.getLogin(),
                user.getEmail()
        ));
    }

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getFullName(),
                    authRequest.getPassword()
                    ));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getFullName());
        String token = jwtTokenUtils.generateToken(userDetails);
        User user = userService.findByFullName(authRequest.getFullName());
        return ResponseEntity.ok(new JwtResponse(token, user));
    }

    public ResponseEntity<?> checkRoles(String token){
        return ResponseEntity.ok(jwtTokenUtils.getRoles(token));
    }
}
