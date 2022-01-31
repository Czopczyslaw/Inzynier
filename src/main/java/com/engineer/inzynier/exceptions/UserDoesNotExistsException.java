package com.engineer.inzynier.exceptions;

import org.springframework.lang.Nullable;

public class UserDoesNotExistsException extends RuntimeException {
    public UserDoesNotExistsException(@Nullable String message) {
        super(message);
    }
}
