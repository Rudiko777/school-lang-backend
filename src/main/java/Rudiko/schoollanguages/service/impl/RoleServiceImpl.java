package Rudiko.schoollanguages.service.impl;

import Rudiko.schoollanguages.model.Role;
import Rudiko.schoollanguages.repository.RoleRepository;
import Rudiko.schoollanguages.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getUserRole(){
        return roleRepository.findByName("ROLE_USER");
    }
}
