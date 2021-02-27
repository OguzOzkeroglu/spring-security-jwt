package com.foo.springsecurityjwt.dto;

import com.foo.springsecurityjwt.model.User;
import lombok.Data;

/**
 * @author Oguz Ozkeroglu
 * Created on 2021.02.27
 */

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String businessTitle;

    public User getUserFromDto(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .phone(phone)
                .name(name)
                .businessTitle(businessTitle)
                .build();
    }
}
