package com.kobi.flyme.customRepository;

import com.kobi.flyme.model.Passenger;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerCustomRepository {
    Passenger findById(int id);
    List<Passenger> findAllByAirlineId(int airlineId);
    Passenger findByEmail(String email);
    List<Passenger> findAll();
    Passenger save(@Valid Passenger passenger);
    boolean deleteById(int id);
    Passenger update(int id, @Valid Passenger passenger);
    Passenger topUp(int passengerId, float amount);
    Passenger topUp(Passenger passenger, float amount);
    Passenger refundTicketPrice(int passengerId, float amount);
    Passenger refundTicketPrice(Passenger passenger, float amount);
    Passenger payTicketPrice(int passengerId, float amount);
    Passenger payTicketPrice(Passenger passenger, float amount);
}
