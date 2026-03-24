package com.example.cartsystem.controller;

import com.example.cartsystem.entity.*;
import com.example.cartsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<CartItem> getCart(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElseThrow();
        return cartService.getCartItems(user);
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody Map<String, Object> request, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElseThrow();
        Long productId = Long.valueOf(request.get("productId").toString());
        Integer quantity = Integer.valueOf(request.get("quantity").toString());
        return cartService.addToCart(productId, quantity, user);
    }
}
