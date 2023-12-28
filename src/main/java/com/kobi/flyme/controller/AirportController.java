package com.kobi.flyme.controller;

import com.kobi.flyme.model.Airport;
import com.kobi.flyme.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airports")

public class AirportController {
    @Autowired
    AirportService service;

    @GetMapping
    public ResponseEntity<?> getAllAirports(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAirportById(@PathVariable("id") int id){
        Airport airport = service.findById(id);
        return airport != null ?  ResponseEntity.ok(airport) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createAirport(@RequestBody Airport airport){
        Airport savedAirport = service.save(airport);
        return savedAirport != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedAirport) : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAirport(@PathVariable("id") int id){
        boolean isDeleted = service.deleteById(id);
        return isDeleted  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAirport(@PathVariable("id") int id, @RequestBody Airport airport){
        Airport toUpdate = service.update(id, airport);
        return toUpdate != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(toUpdate) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
