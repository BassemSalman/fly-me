package com.kobi.flyme.customRepository;

import com.kobi.flyme.model.Airport;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportCustomRepository {
    Airport findById(int id);
    List<Airport> findAll();
    Airport save(@Valid Airport airport);
    boolean deleteById(int id);
    Airport update(int id, @Valid Airport airport);
}
