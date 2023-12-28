package com.kobi.flyme.repository;

import com.kobi.flyme.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
    Airport findById(int id);
    List<Airport> findAll();
    Airport save(Airport airport);
    void deleteById(int id);
}
