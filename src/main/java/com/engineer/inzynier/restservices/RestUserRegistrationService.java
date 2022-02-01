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

    public RestUserLoginOutput registerUser(UserRegistrationDTO dto) throws UserExistsException {
        if (doUserExists(dto)) {
            throw new UserExistsException("User with username: " + dto.getUsername() + " exists!");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUserRole(Role.USER);
        user.setUid(UUID.randomUUID().toString());
        user.setCreated(Date.from(Instant.now()));

        userDAO.addUser(user);

        return new RestUserLoginOutput(user.getUsername(), user.getUid());
    }

    private Boolean doUserExists(UserRegistrationDTO dto) {
        return userDAO.getUserList()
                .stream()
                .map(User::getUsername)
                .anyMatch(username -> username.equals(dto.getUsername()));
    }
}
