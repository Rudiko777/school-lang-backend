package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.dtos.RegistrationUserDto;
import Rudiko.schoollanguages.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findUserById(Long userId);
    List<User> allUsers();
    User saveUser(RegistrationUserDto registrationUserDto);
    boolean deleteUser(Long userId);
    List<User> userGetList(Long idMin);
    Optional<User> findByFullName(String fullName);
}
