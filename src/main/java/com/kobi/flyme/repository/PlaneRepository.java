package com.kobi.flyme.repository;

import com.kobi.flyme.model.Airline;
import com.kobi.flyme.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Integer> {
    Plane findById(int id);
    List<Plane> findAll();
    Plane save(Plane plane);
    void deleteById(int id);

    List<Plane> findAllByPlaneAirline(Airline airline);
    Plane findByIdAndPlaneAirline(int id, Airline planeAirline);
}
