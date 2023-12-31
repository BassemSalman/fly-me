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
    Airline increaseProfit(Airline airline, float amount);
    Airline increaseProfit(int airlineId, float amount);
    Airline decreaseProfit(Airline airline, float amount);
    Airline decreaseProfit(int airlineId, float amount);
}
