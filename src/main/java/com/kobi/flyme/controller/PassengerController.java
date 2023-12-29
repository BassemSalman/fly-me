package com.kobi.flyme.controller;

import com.kobi.flyme.model.Passenger;
import com.kobi.flyme.service.PassengerService;
import com.kobi.flyme.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passengers")

public class PassengerController {
    @Autowired
    PassengerService service;
    @Autowired
    ReservationService reservationService;

    @GetMapping
    public ResponseEntity<?> getAllPassengers(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPassengerById(@PathVariable("id") int id){
        Passenger passenger = service.findById(id);
        return passenger != null ?  ResponseEntity.ok(passenger) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createPassenger(@RequestBody Passenger passenger){
        Passenger savedPassenger = service.save(passenger);
        return savedPassenger != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedPassenger) : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> discardPassenger(@PathVariable("id") int id) {
        boolean isDiscarded = reservationService.discardPassenger(id);
        return isDiscarded  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePassenger(@PathVariable("id") int id, @RequestBody Passenger passenger){
        Passenger toUpdate = service.update(id, passenger);
        return toUpdate != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(toUpdate) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/topup/{passengerId}/amount/{amount}")
    public ResponseEntity<?> topUpBalance(@PathVariable("passengerId") int passengerId, @PathVariable("amount") float amount){
        Passenger passenger = service.topUp(passengerId, amount);
        return passenger != null  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
