package com.kobi.flyme.customRepository;

import com.kobi.flyme.model.Flight;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightCustomRepository {
    List<Flight> findAllAvailable(); // Not full and In Future
    List<Flight> findAllInFutureByPassengerId(int passengerId);
    List<Flight> findAllInFutureByAirlineId(int airlineId);
    List<Flight> findAllByAirlineId(int airlineId);
    List<Flight> findAllInFuture();
    Flight findById(int id);
    List<Flight> findAll();
    Flight save(@Valid Flight flight);
    boolean deleteById(int id);
}
