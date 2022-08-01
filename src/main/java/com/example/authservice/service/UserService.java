package com.example.authservice.service;

import com.example.authservice.model.User;
import com.example.authservice.model.UserDto;
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

    public User addNewUser(UserDto userDto) throws Exception {
        if (getUserByUsername(userDto.getUsername()) != null) {
            throw new Exception("Already have this user!");
        }
        User user = new User (userDto.getUsername(),
                encoder.passwordEncoder().encode(userDto.getPassword()),
                userDto.getEmail(),
                userDto.getAddress(),
                userDto.getCompanyName(),
                userDto.getFulname());
        return userRepository.save(user);
    }
}