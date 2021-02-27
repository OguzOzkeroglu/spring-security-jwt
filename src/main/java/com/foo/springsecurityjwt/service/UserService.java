package com.foo.springsecurityjwt.service;

import com.foo.springsecurityjwt.dto.UserDto;
import com.foo.springsecurityjwt.exception.ResourceNotFoundException;
import com.foo.springsecurityjwt.model.Role;
import com.foo.springsecurityjwt.model.User;
import com.foo.springsecurityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Oguz Ozkeroglu
 * Created on 2021.02.27
 */

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        Set<SimpleGrantedAuthority> authorities = getAuthorityByUser(user);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    private Set<SimpleGrantedAuthority> getAuthorityByUser(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });

        return authorities;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given username:" + username));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(UserDto userDto) {
        User user = userDto.getUserFromDto();
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        Role role = roleService.findRoleByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        // TODO: improve this logic
        if(user.getEmail().split("@")[1].equals("admin.edu")){
            role = roleService.findRoleByName("ADMIN");
            roles.add(role);
        }

        user.setRoles(roles);

        return userRepository.save(user);
    }
}
