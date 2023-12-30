package com.kobi.flyme.controller;

import com.kobi.flyme.model.Plane;
import com.kobi.flyme.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planes")

public class PlaneController {
    @Autowired
    PlaneService service;

    @GetMapping
    public ResponseEntity<?> getAllPlanes(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/available")
    public ResponseEntity<?> getAllAvailablePlanes(){
        return ResponseEntity.ok(service.findAllAvailable());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaneById(@PathVariable("id") int id){
        Plane plane = service.findById(id);
        return plane != null ?  ResponseEntity.ok(plane) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> createPlane(@RequestBody Plane plane){
        Plane savedPlane = service.save(plane);
        return savedPlane != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedPlane) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePlane(@PathVariable("id") int id, @RequestBody Plane plane){
        Plane toUpdate = service.update(id, plane);
        return toUpdate != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(toUpdate) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlane(@PathVariable("id") int id){
        boolean isDeleted = service.deleteById(id);
        return isDeleted ? ResponseEntity.ok(HttpStatus.ACCEPTED) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}