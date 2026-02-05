package com.example.demo.web;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
@CrossOrigin // allows frontend apps to call this API
public class HelloController {

    private List<User> users = new ArrayList<>();

    // Test endpoint
    @GetMapping
    public String sayHello() {
        return "Hello from Spring Boot API 🚀";
    }

    // Get all users
    @GetMapping("/users")
    public List<User> getUsers() {
        return users;
    }

    // Add new user
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        user.setId((long) (users.size() + 1));
        users.add(user);
        return user;
    }
}
