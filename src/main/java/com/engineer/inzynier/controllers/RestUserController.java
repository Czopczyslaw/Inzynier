package com.engineer.inzynier.controllers;

import com.engineer.inzynier.dto.DeviceRegistrationDTO;
import com.engineer.inzynier.dto.UserLoginDTO;
import com.engineer.inzynier.dto.UserRegistrationDTO;
import com.engineer.inzynier.exceptions.UserDoesNotExistsException;
import com.engineer.inzynier.exceptions.UserExistsException;
import com.engineer.inzynier.exceptions.WrongUserPasswordException;
import com.engineer.inzynier.restoutput.DeviceRegisteredOutput;
import com.engineer.inzynier.restoutput.RestErrorMessageOutput;
import com.engineer.inzynier.restservices.DeviceRegistrationService;
import com.engineer.inzynier.restservices.RestUserLoginService;
import com.engineer.inzynier.restservices.RestUserRegistrationService;
import com.engineer.inzynier.services.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RestUserController {

    @Autowired
    private RestUserLoginService restUserLoginService;

    @Autowired
    private RestUserRegistrationService restUserRegistrationService;

    @Autowired
    private UserValidationService userValidationService;

    @Autowired
    private DeviceRegistrationService deviceRegistrationService;

    @Autowired
    private Environment env;

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
    }

    @PostMapping("/api/registerUser")
    public Object registerUser(@RequestBody UserRegistrationDTO dto, @RequestHeader String appKey) {
        String applicationKey = env.getProperty("application.key");

        if (!applicationKey.equals(appKey)) {
            return new RestErrorMessageOutput("Application authentication failed");
        }

        Map<String, String> validationErrors = userValidationService.validateUserData(dto);

        if (!validationErrors.isEmpty()) {
            String errorMessage = "";
            for (String key : validationErrors.keySet()) {
                errorMessage += validationErrors.get(key);
            }
            return new RestErrorMessageOutput(errorMessage);
        } else {
            try {
                return restUserRegistrationService.registerUser(dto);
            } catch (UserExistsException ex) {
                return new RestErrorMessageOutput("User with this name exists!");
            }
        }
    }

    @PostMapping("/api/registerDevice")
    public Object registerDevice(@RequestBody DeviceRegistrationDTO dto, @RequestHeader String appKey) {
        String applicationKey = env.getProperty("application.key");

        if (!applicationKey.equals(appKey)) {
            return new RestErrorMessageOutput("Application authentication failed");
        }

        deviceRegistrationService.registerDevice(dto);

        return new DeviceRegisteredOutput("Device registered");
    }
}
