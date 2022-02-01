package com.engineer.inzynier.services;

import com.engineer.inzynier.dto.UserRegistrationDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserValidationService {
    private final String USERNAME_VAL_RES = "usernameValRes";
    private final String EMAIL_VAL_RES = "emailValRes";
    private final String PASSWORD_VAL_RES = "passwordValRes";

    public Map<String, String> validateUserData(UserRegistrationDTO dto) {
        HashMap<String, String> errorsResult = new HashMap<>();

        if (dto.getUsername().isEmpty() || dto.getUsername().length() < 3) {
            errorsResult.put(USERNAME_VAL_RES, "Name is needed. At least 3 characters long");
        }

        if (dto.getPassword().isEmpty() || dto.getPassword().length() < 8) {
            errorsResult.put(PASSWORD_VAL_RES, "Password is needed. At least 8 characters long");
        }

        if (dto.getEmail().isEmpty() || !dto.getEmail().matches("^\\w+@\\w+\\.\\w+")) {
            errorsResult.put(EMAIL_VAL_RES, "Wrong email format");
        }

        return errorsResult;
    }


}
