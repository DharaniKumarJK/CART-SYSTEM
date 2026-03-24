package com.example.cartsystem.controller;

import com.example.cartsystem.entity.*;
import com.example.cartsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/place")
    public Order placeOrder(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElseThrow();
        return orderService.placeOrder(user);
    }

    @GetMapping
    public List<Order> getOrders(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElseThrow();
        return orderService.getOrdersByUser(user);
    }
}
