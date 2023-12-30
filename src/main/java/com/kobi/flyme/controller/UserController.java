package com.kobi.flyme.controller;

import com.kobi.flyme.DTO.RegisterUserDTO;
import com.kobi.flyme.DTO.UserDTO;
import com.kobi.flyme.service.ReservationService;
import com.kobi.flyme.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    UserService service;
    @Autowired
    ReservationService reservationService;
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/admins")
    public ResponseEntity<?> getAllAdmins(){
        return ResponseEntity.ok(service.findAllAdmins());
    }
    @PostMapping("/admins")
    public ResponseEntity<?> createNewAdmin(@RequestBody @Valid RegisterUserDTO registerUserDTO){
        UserDTO savedUser = service.createNewAdmin(registerUserDTO);
        return savedUser != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedUser) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @DeleteMapping("admins/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") int id){
        boolean isDeleted = service.deleteById(id);
        return isDeleted  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @GetMapping("/clients")
    public ResponseEntity<?> getAllClients(){
        return ResponseEntity.ok(service.findAllClients());
    }

    @PostMapping("/clients")
    public ResponseEntity<?> createNewClient(@RequestBody RegisterUserDTO registerUserDTO){
        UserDTO savedUser = service.createNewClient(registerUserDTO);
        return savedUser != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedUser) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @DeleteMapping("clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") int id){ // discards all airline data if last admin to delete
        boolean isDeleted = reservationService.discardClient(id);
        return isDeleted  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @GetMapping("/{id}/reset-pass")
    public ResponseEntity<?> getUserResetPass(@PathVariable("id") int id){
        return ResponseEntity.ok(service.getIsResetPassById(id));
    }
    @PostMapping("/{id}/reset-pass")
    public ResponseEntity<?> resetPassword(@PathVariable("id") int id){
        return ResponseEntity.ok(service.resetPassword(id));
    }


}
