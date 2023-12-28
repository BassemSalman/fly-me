package com.kobi.flyme.customRepository;

import com.kobi.flyme.model.Airline;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineCustomRepository {
    Airline findById(int id);
    List<Airline> findAll();
    Airline save(@Valid Airline airline);
    Airline update(int id, @Valid Airline airline);
    float findProfitById(int id);
    boolean deleteById(int id);
}
