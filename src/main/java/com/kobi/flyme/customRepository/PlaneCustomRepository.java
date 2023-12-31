package com.kobi.flyme.customRepository;

import com.kobi.flyme.model.Plane;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaneCustomRepository  {
    List<Plane> findAllAvailable();
    List<Plane> findAllAvailableByAirlineId(int airlineId);
    List<Plane> findAll();
    List<Plane> findAllByAirlineId(int airlineId);

    Plane findById(int id);
    Plane save(Plane plane);
    boolean deleteById(int id);
    Plane update(int id, @Valid Plane plane);
//    Plane bookPlane(int id);
//    Plane unbookPlane(int id);
//    Plane bookPlane(Plane plane);
//    Plane unbookPlane(Plane plane);

}
