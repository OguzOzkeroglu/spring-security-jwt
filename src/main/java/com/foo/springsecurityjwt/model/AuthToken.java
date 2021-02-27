package com.foo.springsecurityjwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Oguz Ozkeroglu
 * Created on 2021.02.27
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {
    private String token;
}
