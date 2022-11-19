package com.engineer.inzynier.services;

import com.engineer.inzynier.dao.UserDAO;
import com.engineer.inzynier.entities.User;
import com.engineer.inzynier.entities.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoService {
    @Autowired
    UserDAO userDAO;

    public Map<String, String> getUserInfo() {
        Map<String, String> userInfo = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication.getPrincipal() instanceof String)) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

            User user = userDAO.getUserByUid(userPrincipal.getUID());



            userInfo.put("username", user.getUsername());
            userInfo.put("gender", String.valueOf(user.getGender()));
            userInfo.put("weight", String.valueOf(user.getWeight()));
            userInfo.put("height", String.valueOf(user.getHeight()));
            userInfo.put("birthday", String.valueOf(user.getBirthday()));
        }

        return userInfo;
    }
}
