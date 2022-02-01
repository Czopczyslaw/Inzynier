package com.engineer.inzynier.controllers;

import com.engineer.inzynier.dto.UserRegistrationDTO;
import com.engineer.inzynier.exceptions.UserExistsException;
import com.engineer.inzynier.services.UserRegistrationService;
import com.engineer.inzynier.services.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;


@Controller
public class UserController {
    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserValidationService userValidationService;

    @GetMapping("/register")
    public String registerForm(Map<String, Object> model) {
        model.put("form", new UserRegistrationDTO());
        return "registerForm";
    }

    @PostMapping("/register")
    public String registerEffect(@ModelAttribute UserRegistrationDTO dto, Map<String, Object> model) {
        Map<String, String> validationErrors = userValidationService.validateUserData(dto);

        model.put("form", dto);

        if (!validationErrors.isEmpty()) {
            model.putAll(validationErrors);
            return "registerForm";
        } else {
            try {
                userRegistrationService.registerUser(dto);
            } catch (UserExistsException ex) {
                model.put("userExistsException", "User with this username exists!");
                return "registerForm";
            }
            return "index";
        }
    }
}
