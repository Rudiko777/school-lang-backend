package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.model.Role;
import Rudiko.schoollanguages.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole(){
        return roleRepository.findByName("ROLE_USER");
    }
}
