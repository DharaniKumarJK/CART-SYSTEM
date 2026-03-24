package com.example.cartsystem.config;

import com.example.cartsystem.entity.Role;
import com.example.cartsystem.repository.RoleRepository;
import com.example.cartsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_USER"));
        }
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
        }

        // Seed an admin user if not exists
        if (userService.findByUsername("admin").isEmpty()) {
            userService.registerUser("admin", "admin123", "ROLE_ADMIN");
        }
        
        // Seed a sample user if not exists
        if (userService.findByUsername("user").isEmpty()) {
            userService.registerUser("user", "user123", "ROLE_USER");
        }
    }
}
