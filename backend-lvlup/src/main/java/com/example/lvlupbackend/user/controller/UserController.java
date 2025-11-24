package com.example.lvlupbackend.user.controller;

import com.example.lvlupbackend.user.model.User;
import com.example.lvlupbackend.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.save(user);
    }

    @DeleteMapping("/id")
    public void deleteUser(@PathVariable Integer id) {
        service.delete(id);
    }
}
