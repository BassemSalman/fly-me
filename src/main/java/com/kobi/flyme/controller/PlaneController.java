package com.kobi.flyme.controller;

import com.kobi.flyme.model.Plane;
import com.kobi.flyme.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planes")

// TODO: Planes, Flights, should have airline specific endpoints plus the general ones for admin

public class PlaneController {
    @Autowired
    PlaneService service;



    @GetMapping
    public ResponseEntity<?> getPlaneByIdandAirlineId(@PathVariable("id") int id){
        return ResponseEntity.ok(service.findAllByAirlineId(id));
    }

    @PostMapping("/{airlineId}")
        public ResponseEntity<?> createPlane(@RequestBody Plane plane){
            Plane savedPlane = service.save(plane);
            return savedPlane != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedPlane) : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlane(@PathVariable("id") int id){
        boolean isDeleted = service.deleteById(id);
        return isDeleted  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlane(@PathVariable("id") int id, @RequestBody Plane plane){
        Plane toUpdate = service.update(id, plane);
        return toUpdate != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(toUpdate) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
