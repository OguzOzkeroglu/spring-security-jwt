package com.foo.springsecurityjwt.service;

import com.foo.springsecurityjwt.exception.ResourceNotFoundException;
import com.foo.springsecurityjwt.model.Role;
import com.foo.springsecurityjwt.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Oguz Ozkeroglu
 * Created on 2021.02.27
 */
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with given name:" + name));
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
