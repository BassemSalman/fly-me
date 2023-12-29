package com.kobi.flyme.service;

import com.kobi.flyme.customRepository.PlaneCustomRepository;
import com.kobi.flyme.model.Airline;
import com.kobi.flyme.model.Plane;
import com.kobi.flyme.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneService implements PlaneCustomRepository {
    @Autowired
    private PlaneRepository repo;
    @Autowired
    private AirlineService airlineService;
    public Plane save(Plane plane){
        plane.setAvailable(true);
        return repo.save(plane);
    }

    public List<Plane> findAll(){
        return repo.findAll();
    }

    public Plane findByIdAndAirlineId(int id, int airlineId) {
        Airline airline = airlineService.findById(id);
        if(airline == null) return null;
        return repo.findByIdAndPlaneAirline(id, airline);
    }

    public List<Plane> findAllByAirlineId(int airlineId) {
        Airline airline = airlineService.findById(airlineId);
        if(airline == null) return null;
        return repo.findAllByPlaneAirline(airline);
    }



    public Plane findById(int id){
        return repo.findById(id);
    }

    public boolean deleteById(int id){
        if(repo.findById(id) == null) return false;
        repo.deleteById(id);
        return repo.findById(id) == null;
    }

    // Allowed attributess: model, name
    public Plane update(int id, Plane updated){
        Plane toUpdate = repo.findById(id);
        if(toUpdate != null) {
            if(updated.getName() != null) toUpdate.setName(updated.getName());
            if(updated.getModel() != null) toUpdate.setModel(updated.getModel());
            return repo.save(toUpdate);
        }
        return repo.save(updated);
    }
    public Plane bookPlane(int id) {
        Plane plane = repo.findById(id);
        if(plane == null) return null;
        plane.setAvailable(false);
        return repo.save(plane);
    }

    public Plane unbookPlane(int id) {
        Plane plane = repo.findById(id);
        if(plane == null) return null;
        plane.setAvailable(true);
        return repo.save(plane);
    }

    public Plane bookPlane(Plane plane) {
        if(plane == null) return null;
        plane.setAvailable(false);
        return repo.save(plane);
    }

    public Plane unbookPlane(Plane plane) {
        if(plane == null) return null;
        plane.setAvailable(true);
        return repo.save(plane);
    }

    public List<Plane> findAllByIsAvailableTrue() {
        return repo.findAllByIsAvailableTrue();
    }

}
