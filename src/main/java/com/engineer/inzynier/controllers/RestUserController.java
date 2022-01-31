package com.engineer.inzynier.controllers;

import com.engineer.inzynier.dto.UserLoginDTO;
import com.engineer.inzynier.dto.UserRegistrationDTO;
import com.engineer.inzynier.exceptions.UserDoesNotExistsException;
import com.engineer.inzynier.exceptions.UserExistsException;
import com.engineer.inzynier.exceptions.WrongUserPasswordException;
import com.engineer.inzynier.restoutput.RestErrorMessageOutput;
import com.engineer.inzynier.restoutput.RestUserLoginOutput;
import com.engineer.inzynier.restservices.RestUserLoginService;
import com.engineer.inzynier.restservices.RestUserRegistrationService;
import com.engineer.inzynier.services.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class RestUserController {

    @Autowired
    private RestUserLoginService restUserLoginService;
    @Autowired
    private RestUserRegistrationService restUserRegistrationService;
    @Autowired
    private Environment env;

    @GetMapping("/api/loginUser")
    public RestUserLoginOutput loginUser(@RequestBody UserLoginDTO userLoginDTO, @RequestHeader String appKey) throws RestErrorMessageOutput {
        String applicationKey = env.getProperty("application.key");

        if (!applicationKey.equals(appKey)) throw new RestErrorMessageOutput("Application authentication failed!");
        try {
            return restUserLoginService.loginUser(userLoginDTO);
        } catch (Exception ex) {
            if (ex instanceof UserDoesNotExistsException) throw new RestErrorMessageOutput("User does not exists!");
            if (ex instanceof WrongUserPasswordException) throw new RestErrorMessageOutput("Wrong user password!");
            throw new RestErrorMessageOutput("Unknown error!");
        }
    }

    @GetMapping("/api/registerUser")
    public RestUserLoginOutput registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO, @RequestHeader String appKey) throws RestErrorMessageOutput {
        String applicationKey = env.getProperty("application.key");
        if (!applicationKey.equals(appKey)) throw new RestErrorMessageOutput("Application authentication failed!");

        HashMap<String, String> validationErrors = new UserValidationService().validateUserData(userRegistrationDTO);

        if (!validationErrors.isEmpty()) {
            String errorMessage = "";
            validationErrors.forEach((error, errorvVal) ->
                    errorMessage.concat(errorvVal)
            );
            throw new RestErrorMessageOutput(errorMessage);
        } else {
            try {
                return restUserRegistrationService.registerUser(userRegistrationDTO);
            } catch (UserExistsException ex) {
                throw new RestErrorMessageOutput("User with this name exists!");
            }
        }

    }
}
