package com.kobi.flyme.repository;

import com.kobi.flyme.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {
    Airline findById(int id);
    List<Airline> findAll();
    Airline save(Airline airline);
    void deleteById(int id);

}
