package com.kobi.flyme.repository;

import com.kobi.flyme.model.Passenger;
import com.kobi.flyme.model.Plane;
import org.springframework.data.repository.CrudRepository;

public interface PlaneRepository extends CrudRepository<Plane, Integer> {
}
