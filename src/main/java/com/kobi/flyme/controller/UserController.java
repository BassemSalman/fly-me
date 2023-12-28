package com.kobi.flyme.controller;

import com.kobi.flyme.model.User;
import com.kobi.flyme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        user.setPassword(null);
        user.setResetPass(true);
        User savedUser = service.save(user);
        return savedUser != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedUser) : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
        boolean isDeleted = service.deleteById(id);
        return isDeleted  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
