package Rudiko.schoollanguages.service.impl;

import Rudiko.schoollanguages.dtos.RegistrationUserDto;
import Rudiko.schoollanguages.model.User;
import Rudiko.schoollanguages.repository.UserRepository;
import Rudiko.schoollanguages.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @PersistenceContext
    private EntityManager em;
    UserRepository userRepository;
    RoleServiceImpl roleService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String fullName = (String) authentication.getPrincipal();
        return userRepository.findByFullName(fullName);
    }

    @Override
    public void addLanguageCourseToUser(Long userId, Long languageCourseId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }


        User user = optionalUser.get();

        List<Long> languageCourses = user.getLanguageCourses();
        if (languageCourses == null) {
            languageCourses = new ArrayList<>();
        }
        if (languageCourses.contains(languageCourseId)){
            return;
        }

        languageCourses.add(languageCourseId);
        user.setLanguageCourses(languageCourses);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteLanguageCourseOfUser(Long userId, Long languageCourseId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        List<Long> languageCourses = user.getLanguageCourses();
        if (languageCourses == null) {
            languageCourses = new ArrayList<>();
        }

        languageCourses.remove(languageCourseId);
        user.setLanguageCourses(languageCourses);

        userRepository.save(user);
        return ResponseEntity.ok().body("Response Body");
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    @Override
    @ResponseBody
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(RegistrationUserDto registrationUserDto) {
        User user = new User();
        user.setFullName(registrationUserDto.getFullName());
        user.setBirthday(registrationUserDto.getBirthday());
        user.setGender(registrationUserDto.getGender());
        user.setEmail(registrationUserDto.getEmail());
        user.setPhoneNumber(registrationUserDto.getPhoneNumber());
        user.setPasswordConfirm(registrationUserDto.getConfirmPassword());
        user.setLogin(registrationUserDto.getLogin());
        user.setRoles(List.of(roleService.getUserRole()));
        user.setPassword(bCryptPasswordEncoder.encode(registrationUserDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<User> userGetList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    @Override
    public User findByFullName(String fullName) {
        return userRepository.findByFullName(fullName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String fullName) throws UsernameNotFoundException {
        User user = findByFullName(fullName);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }
}
