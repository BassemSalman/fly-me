package com.kobi.flyme.repository;

import com.kobi.flyme.model.Airport;
import com.kobi.flyme.model.Airway;
import org.springframework.data.repository.CrudRepository;

public interface AirwayRepository extends CrudRepository<Airway, Integer> {
}
