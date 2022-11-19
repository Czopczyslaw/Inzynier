package com.engineer.inzynier.exceptions;

import org.springframework.lang.Nullable;

public class DataRegisterException extends RuntimeException {
    public DataRegisterException(@Nullable String message) {
        super(message);
    }
}

