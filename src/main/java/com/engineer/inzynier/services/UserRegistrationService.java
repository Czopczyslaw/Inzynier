package com.engineer.inzynier.services;

import com.engineer.inzynier.dao.UserDAO;
import com.engineer.inzynier.dto.UserRegistrationDTO;
import com.engineer.inzynier.entities.Role;
import com.engineer.inzynier.entities.User;
import com.engineer.inzynier.exceptions.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class UserRegistrationService {

    @Autowired
    private UserDAO userDao;

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
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

        userDao.addUser(user);
    }

    private boolean doUserExists(UserRegistrationDTO userRegistrationDTO) {
        return userDao.getUserList().stream().map(User::getUsername).anyMatch(login -> login.equals(userRegistrationDTO.getUsername()));
    }

}
