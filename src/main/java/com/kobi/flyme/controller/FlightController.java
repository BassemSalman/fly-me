package com.kobi.flyme.controller;

import com.kobi.flyme.model.Flight;
import com.kobi.flyme.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")

public class FlightController {
    @Autowired
    FlightService service;

    @GetMapping
    public ResponseEntity<?> getAllFlights(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable("id") int id){
        Flight flight = service.findById(id);
        return flight != null ?  ResponseEntity.ok(flight) : ResponseEntity.notFound().build();
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableFlights(){
        return ResponseEntity.ok(service.findAllAvailableAndInFuture());
    }


    @PostMapping("/{id}")
    public ResponseEntity<?> createFlight(@RequestBody Flight flight){
        Flight savedFlight = service.save(flight);
        return savedFlight != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedFlight) : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable("id") int id){
        boolean isDeleted = service.deleteById(id);
        return isDeleted  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
