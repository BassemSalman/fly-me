package com.kobi.flyme.customRepository;

import com.kobi.flyme.model.Flight;
import com.kobi.flyme.model.Passenger;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightCustomRepository {
    List<Flight> findAllNotFullAndInFuture();
    List<Flight> findAllInFutureByPassenger(Passenger passenger);
    List<Flight> findAllInFuture();
    Flight findById(int id);
    List<Flight> findAll();
    Flight save(@Valid Flight flight);
    boolean deleteById(int id);
}
