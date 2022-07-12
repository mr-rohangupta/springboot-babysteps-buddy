package com.springboot.crud.controller;

import com.springboot.crud.exception.ResourceNotFoundException;
import com.springboot.crud.model.User;
import com.springboot.crud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author rohangupta
 */
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        log.info("Get all users list ..!!");
        List<User> allUsers = Collections.unmodifiableList(userService.getAllUsers());
        if (allUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        log.info("Trying to fetch the user information with id {}", id);
        User user = userService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with ID : " + id + " Not Found!"));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("Request received for user {}", user);
        User persistedUser = userService.saveUser(user);
        return new ResponseEntity<>(persistedUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
                                           @RequestBody User user) {
        log.info("Request received for updating user ..");
        User userFromDB = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id ::" + id));
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setJobTitle(user.getJobTitle());
        final User updatedUser = userService.saveUser(userFromDB);
        return ResponseEntity.ok(updatedUser);
    }
}

