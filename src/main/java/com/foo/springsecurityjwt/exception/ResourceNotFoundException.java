package com.foo.springsecurityjwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Oguz Ozkeroglu
 * Created on 2021.02.27
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5993858779937499473L;

    public ResourceNotFoundException(Long id) {
        super("Resource not found with given id: " + id);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
