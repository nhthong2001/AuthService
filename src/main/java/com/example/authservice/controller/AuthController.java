package com.example.authservice.controller;

import com.example.authservice.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.authservice.model.User;
import com.example.authservice.service.AuthService;
import com.example.authservice.service.UserService;
import com.example.authservice.util.JwtUtil;

@RestController
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody UserDto request) {

        if (authService.validUser(request.getUsername(), request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }

        return new ResponseEntity<String>("Not Valid User!", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody UserDto request) {
        try {
            User registedUser = userService.addNewUser(request.getUsername(), request.getPassword());
            // Persist user to some persistent storage
            System.out.println("Info saved...");

            return new ResponseEntity<String>("Registered user \"" + registedUser.getUsername() + "\"!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }
    @GetMapping("/auth/test")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok().body("Welcome!!!");
    }

}
