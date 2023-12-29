package com.kobi.flyme.controller;


import com.kobi.flyme.model.ReservationRequest;
import com.kobi.flyme.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    ReservationService service;

    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@RequestBody @Valid ReservationRequest request){
        boolean successfullyBooked = service.bookFlight(request.getPassengerId(), request.getFlightId());
        return successfullyBooked  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("/unbook")
    public ResponseEntity<?> unbookFlight(@RequestBody @Valid ReservationRequest request){
        boolean successfullyUnbooked = service.unbookFlight(request.getPassengerId(), request.getFlightId());
        return successfullyUnbooked  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    @DeleteMapping("/flights/{id}")
//    public ResponseEntity<?> cancelFlight(@PathVariable("id") int id){
//        boolean isCancelled = service.cancelFlight(id);
//        return isCancelled ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//    @DeleteMapping("/passengers/{id}")
//    public ResponseEntity<?> discadPassenger(@PathVariable("id") int id){
//        boolean isDiscarded = service.discardPassenger(id);
//        return isDiscarded  ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }

}
