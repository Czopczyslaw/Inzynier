package com.engineer.inzynier.dao;

import com.engineer.inzynier.entities.User;
import com.engineer.inzynier.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDAO {
    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByUid(String uid) {
        return userRepository.findByUid(uid);
    }
}
