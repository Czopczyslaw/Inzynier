package com.engineer.inzynier.restservices;

import com.engineer.inzynier.dao.UserDAO;
import com.engineer.inzynier.dto.UserLoginDTO;
import com.engineer.inzynier.entities.User;
import com.engineer.inzynier.exceptions.UserDoesNotExistsException;
import com.engineer.inzynier.exceptions.WrongUserPasswordException;
import com.engineer.inzynier.restoutput.RestUserLoginOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RestUserLoginService {
    @Autowired
    private UserDAO userDAO;

    public RestUserLoginOutput loginUser(UserLoginDTO userLoginDTO) {
        User user = null;
        try {
            user = userDAO.getUserByUsername(userLoginDTO.getUsername());
        } catch (Exception ex) {
            throw new UserDoesNotExistsException("User ${userLoginDTO.username} does not exists");
        }
        if (!new BCryptPasswordEncoder().matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new WrongUserPasswordException("Wrong password for user ${userLoginDTO.username}");
        }
        return new RestUserLoginOutput(user.getUsername(), user.getUid());
    }
}
