package com.kobi.flyme.customRepository;

import com.kobi.flyme.model.Passenger;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerCustomRepository {
    Passenger findById(int id);
    List<Passenger> findAll();
    Passenger save(@Valid Passenger passenger);
    boolean deleteById(int id);
    Passenger update(int id, @Valid Passenger passenger);
    boolean bookFlight(int passengerId, int flightId);
    boolean unbookFlight(int passengerId, int flightId);
    Passenger topUp(int passengerId, float amount);
}
