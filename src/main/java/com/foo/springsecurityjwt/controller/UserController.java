package com.foo.springsecurityjwt.controller;

import com.foo.springsecurityjwt.configuration.TokenProvider;
import com.foo.springsecurityjwt.dto.UserDto;
import com.foo.springsecurityjwt.model.AuthToken;
import com.foo.springsecurityjwt.model.LoginUser;
import com.foo.springsecurityjwt.model.User;
import com.foo.springsecurityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Oguz Ozkeroglu
 * Created on 2021.02.27
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @PostMapping("/register")
    public User saveUser(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adminping")
    public String adminPing() {
        return "Only Admins Can Read This";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/userping")
    public String userPing() {
        return "Any User Can Read This";
    }
}
