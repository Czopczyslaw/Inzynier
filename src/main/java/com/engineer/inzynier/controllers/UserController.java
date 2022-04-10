package com.engineer.inzynier.controllers;

import com.engineer.inzynier.dto.UserLoginDTO;
import com.engineer.inzynier.dto.UserRegistrationDTO;
import com.engineer.inzynier.exceptions.UserDoesNotExistsException;
import com.engineer.inzynier.exceptions.UserExistsException;
import com.engineer.inzynier.exceptions.WrongUserPasswordException;
import com.engineer.inzynier.restoutput.RestErrorMessageOutput;
import com.engineer.inzynier.services.UserLoginService;
import com.engineer.inzynier.services.UserRegistrationService;
import com.engineer.inzynier.services.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public String loginEffect(@ModelAttribute UserLoginDTO dto, Map<String,Object> model){

        try{
            return UserLoginService.loginUser(dto);
        }catch(Exception ex){
            if (ex instanceof UserDoesNotExistsException) {
                model.put("userDoesNotExistsException","User does not exists!");
            } else if (ex instanceof WrongUserPasswordException) {
                model.put("userWrongPassword","Wrong user password!");
            } else {
                model.put("userUnknownError","Unknown error!");
            }
            return "login";
        }
    }
    /*
    @PostMapping("/api/loginUser")
    public Object loginUser(@RequestBody UserLoginDTO dto, @RequestHeader String appKey) {
        String applicationKey = env.getProperty("application.key");

        if (!applicationKey.equals(appKey)) {
            return new RestErrorMessageOutput("Application authentication failed");
        }

        try {
            return restUserLoginService.loginUser(dto);
        } catch (Exception ex) {
            if (ex instanceof UserDoesNotExistsException) {
                return new RestErrorMessageOutput("User does not exists!");
            } else if (ex instanceof WrongUserPasswordException) {
                return new RestErrorMessageOutput("Wrong user password!");
            } else {
                return new RestErrorMessageOutput("Unknown error!");
            }
        }
    }*/

}
