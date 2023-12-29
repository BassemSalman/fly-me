package com.kobi.flyme.repository;

import com.kobi.flyme.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

//    @Query("SELECT f FROM Flight f WHERE f.isFull = false AND f.date > CURRENT_TIMESTAMP")
//    List<Flight> findAvailableFlights();

    List<Flight> findAllByisFullFalse();
    Flight findById(int id);
    List<Flight> findAll();
    Flight save(Flight flight);
    void deleteById(int id);
}
