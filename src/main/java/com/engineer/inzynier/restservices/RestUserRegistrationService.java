package com.engineer.inzynier.restservices;

import com.engineer.inzynier.dao.UserDAO;
import com.engineer.inzynier.dto.UserRegistrationDTO;
import com.engineer.inzynier.entities.Role;
import com.engineer.inzynier.entities.User;
import com.engineer.inzynier.exceptions.UserExistsException;
import com.engineer.inzynier.restoutput.RestUserLoginOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RestUserRegistrationService {
    @Autowired
    private UserDAO userDAO;

    public RestUserLoginOutput registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (doUserExists(userRegistrationDTO)) {
            throw new UserExistsException("User with username: ${userRegistrationDTO.username} exists.");
        }
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setUserRole(Role.USER);
        user.setUid(UUID.randomUUID().toString());
        user.setCreated(Date.from(Instant.now()));

        userDAO.addUser(user);

        return new RestUserLoginOutput(user.getUsername(), user.getUid());
    }

    boolean doUserExists(UserRegistrationDTO userRegistrationDTO) {
        return userDAO.getUserList().stream().map(User::getUsername).anyMatch(login -> login.equals(userRegistrationDTO.getUsername()));
    }
}
