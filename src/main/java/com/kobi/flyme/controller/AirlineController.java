package com.kobi.flyme.controller;

import com.kobi.flyme.model.Airline;
import com.kobi.flyme.service.AirlineService;
import com.kobi.flyme.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airlines")

public class AirlineController {
    @Autowired
    AirlineService service;
    @Autowired
    PlaneService planeService;

    @GetMapping
    public ResponseEntity<?> getAllAirlines(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAirlineById(@PathVariable("id") int id){
        Airline airline = service.findById(id);
        return airline != null ?  ResponseEntity.ok(airline) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createAirline(@RequestBody Airline airline){
        Airline savedAirline = service.save(airline);
        return savedAirline != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedAirline) : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAirline(@PathVariable("id") int id){
        boolean isDeleted = service.deleteById(id);
        return isDeleted  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAirline(@PathVariable("id") int id, @RequestBody Airline airline){
        Airline toUpdate = service.update(id, airline);
        return toUpdate != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(toUpdate) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{id}/profit")
    public ResponseEntity<?> getProfit(@PathVariable("id") int id){
        float profit = service.findProfitById(id); // throws exception in case its not found
        return ResponseEntity.ok(profit);
    }

    @GetMapping("/{airlineId}/planes")
    public ResponseEntity<?> getAllPlanesbyAirlineId(@PathVariable("airlineId") int airlineId){
        return ResponseEntity.ok(planeService.findAllByAirlineId(airlineId));
    }
    @GetMapping("/{airlineId}/available-planes")
    public ResponseEntity<?> getAllAvailablePlanesbyAirlineId(@PathVariable("airlineId") int airlineId){
        return ResponseEntity.ok(planeService.findAllAvailableByAirlineId(airlineId));
    }

//    @PostMapping("/{airlineId}/planes")
//    public ResponseEntity<?> addPlaneToAirline(@PathVariable("airlineId") int airlineId, @RequestBody Plane plane){
//        planeService.save(plane);
//        return plane != null ?  ResponseEntity.ok(plane) : ResponseEntity.notFound().build();
//    }
}
