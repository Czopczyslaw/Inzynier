package com.engineer.inzynier.exceptions;

import org.springframework.lang.Nullable;

public class WrongUserPasswordException extends RuntimeException {
    public WrongUserPasswordException(@Nullable String message) {
        super(message);
    }
}
