package com.kobi.flyme.repository;

import com.kobi.flyme.model.Country;
import com.kobi.flyme.model.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Integer> {
}
