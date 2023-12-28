package com.kobi.flyme.customRepository;

import com.kobi.flyme.model.Plane;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaneCustomRepository  {
    Plane findByIdAndAirlineId(int id, int airlineId);
    List<Plane> findAllByAirlineId(int airlineId);
    Plane findById(int id);
    List<Plane> findAll();
    Plane save(Plane plane);
    boolean deleteById(int id);
    Plane update(int id, @Valid Plane plane);
    
}
