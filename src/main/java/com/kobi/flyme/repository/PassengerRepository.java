package com.kobi.flyme.repository;

import com.kobi.flyme.model.Flight;
import com.kobi.flyme.model.Passenger;
import org.springframework.data.repository.CrudRepository;

public interface PassengerRepository extends CrudRepository<Passenger, Integer> {
}
