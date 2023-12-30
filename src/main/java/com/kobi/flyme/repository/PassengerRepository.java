package com.kobi.flyme.repository;

import com.kobi.flyme.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Passenger findById(int id);
    Passenger findByEmail(String email);
    List<Passenger> findAll();
    Passenger save(Passenger passenger);
    void deleteById(int id);

}
