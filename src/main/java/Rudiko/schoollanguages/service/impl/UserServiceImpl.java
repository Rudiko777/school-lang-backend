package Rudiko.schoollanguages.service.impl;

import Rudiko.schoollanguages.dtos.RegistrationUserDto;
import Rudiko.schoollanguages.model.Role;
import Rudiko.schoollanguages.model.User;
import Rudiko.schoollanguages.repository.RoleRepository;
import Rudiko.schoollanguages.repository.UserRepository;
import Rudiko.schoollanguages.service.RoleService;
import Rudiko.schoollanguages.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @PersistenceContext
    private EntityManager em;
    UserRepository userRepository;
    RoleService roleService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
    public Optional<User> findByFullName(String fullName) {
        return userRepository.findByFullName(fullName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String fullName) throws UsernameNotFoundException {
        User user = findByFullName(fullName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }
}
