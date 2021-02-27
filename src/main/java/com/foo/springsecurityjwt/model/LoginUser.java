package com.foo.springsecurityjwt.model;

import lombok.Data;

/**
 * @author Oguz Ozkeroglu
 * Created on 2021.02.27
 */

@Data
public class LoginUser {
    private String username;
    private String password;
}
