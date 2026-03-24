package com.example.cartsystem.controller;

import com.example.cartsystem.entity.User;
import com.example.cartsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String role = request.get("role"); // ROLE_USER or ROLE_ADMIN
        return userService.registerUser(username, password, role);
    }

    @GetMapping("/login")
    public String login() {
        return "Logged in successfully";
    }
}
