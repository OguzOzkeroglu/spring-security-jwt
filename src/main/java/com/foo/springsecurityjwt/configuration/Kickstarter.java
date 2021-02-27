package com.foo.springsecurityjwt.configuration;

import com.foo.springsecurityjwt.model.Role;
import com.foo.springsecurityjwt.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Oguz Ozkeroglu
 * Created on 2021.02.27
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class Kickstarter implements CommandLineRunner {
    private final RoleService roleService;

    @Override
    public void run(String... args) {
        Role roleUser = Role.builder()
                .id(1L)
                .name("USER")
                .description("User role")
                .build();

        Role roleAdmin = Role.builder()
                .id(2L)
                .name("ADMIN")
                .description("Admin role")
                .build();

        roleService.save(roleUser);
        roleService.save(roleAdmin);

        log.info("Role saved :: " + roleUser.getName());
        log.info("Role saved :: " + roleAdmin.getName());
    }
}
