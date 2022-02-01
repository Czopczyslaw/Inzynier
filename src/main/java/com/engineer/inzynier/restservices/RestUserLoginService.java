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

    public RestUserLoginOutput loginUser(UserLoginDTO dto) throws WrongUserPasswordException {
        User user;

        try {
            user = userDAO.getUserByUsername(dto.getUsername());
        } catch (Exception ex) {
            throw new UserDoesNotExistsException("User " + dto.getUsername() + " does not exists");
        }

        if (!new BCryptPasswordEncoder().matches(dto.getPassword(), user.getPassword())) {
            throw new WrongUserPasswordException("Wrong password for user " + dto.getUsername());
        }

        return new RestUserLoginOutput(user.getUsername(), user.getUid());
    }
}
