package com.example.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.authservice.model.User;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public boolean validUser(String username, String password) {
        User user = userService.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return true;
        }
        return false;
    }

}
