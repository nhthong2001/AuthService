package com.example.authservice.service;

import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.util.BCryptEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptEncoder encoder = new BCryptEncoder();


    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public User addNewUser(String username, String password) throws Exception {
        if (getUserByUsername(username) != null) {
            throw new Exception("Already have this user!");
        }
        User user = new User (username, encoder.passwordEncoder().encode(password));
        return userRepository.save(user);
    }



}