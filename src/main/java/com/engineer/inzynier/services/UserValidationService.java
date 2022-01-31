package com.engineer.inzynier.services;

import com.engineer.inzynier.dto.UserRegistrationDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserValidationService {
    private final String USERNAME_VAL_RES = "username";
    private final String PASSWORD_VAL_RES = "password";
    private final String EMAIL_VAL_RES = "email";

    public HashMap<String, String> validateUserData(UserRegistrationDTO dto) {
        HashMap<String, String> errorResult = new HashMap<>();
        if (dto.getUsername().isEmpty() || dto.getUsername().length() < 3)
            errorResult.put(USERNAME_VAL_RES, "Name is needed. At least 3 characters long");
        if (dto.getPassword().isEmpty() || dto.getPassword().length() < 8)
            errorResult.put(PASSWORD_VAL_RES, "Password is needed. At least 8 characters long");
        if (dto.getEmail().isEmpty() || !dto.getEmail().trim().matches("^\\w+@\\w+\\.\\w+"))
            errorResult.put(EMAIL_VAL_RES, "Wrong email format");

        return errorResult;
    }


}
