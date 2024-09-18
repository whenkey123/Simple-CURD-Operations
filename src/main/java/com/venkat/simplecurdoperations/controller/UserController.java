package com.venkat.simplecurdoperations.controller;

import com.venkat.simplecurdoperations.models.User;
import com.venkat.simplecurdoperations.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable final long id) {
        final User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        final User createUser = userService.createUser(user);
        if (createUser != null) {
            return ResponseEntity.ok(createUser);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        final User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
