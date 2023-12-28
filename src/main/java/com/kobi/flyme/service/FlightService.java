package com.kobi.flyme.service;

import com.kobi.flyme.customRepository.FlightCustomRepository;
import com.kobi.flyme.model.Flight;
import com.kobi.flyme.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService implements FlightCustomRepository {
    @Autowired
    private FlightRepository repo;
    public Flight save(Flight flight){
        return repo.save(flight);
    }

    public List<Flight> findAll(){
        return repo.findAll();
    }
    public Flight findById(int id){
        return repo.findById(id);
    }

    public boolean deleteById(int id){
        if(repo.findById(id) == null) return false;
        repo.deleteById(id);
        return repo.findById(id) == null;
    }

    public List<Flight> findAllAvailableAndInFuture(){ // could be enhanced using sql later
        return repo.findAllByAvailableTrue()
                .stream()
                .filter(Flight::isInFuture)
                .collect(Collectors.toList());
    }

    public List<Flight> findAllInFuture(){
        return repo.findAll()
                .stream()
                .filter(Flight::isInFuture)
                .collect(Collectors.toList());
    }
    public List<Flight> findAllInFutureByPassengerId(int passengerId){
        return repo.findAll()
                .stream()
                .filter(Flight::isInFuture)
                .filter(flight -> flight.getFlightPassengers().contains(passengerId))
                .collect(Collectors.toList());
    }
    @Autowired
    PlaneService planeService;
    public Flight update(int id, Flight updated){
        Flight toUpdate = repo.findById(id);
        if(toUpdate != null){
            if(updated.getFlightPlane() != null) toUpdate.setFlightPlane(planeService.findById(updated.getFlightPlane().getId()));
            // notice that isFull is to be altered as well. Done on db level
            // what about passengers who are already
            if(updated.getPassengerCount() != toUpdate.getPassengerCount() && updated.getPassengerCount() != 0)
                toUpdate.setPassengerCount(updated.getPassengerCount());

            return repo.save(toUpdate);
        }

        return repo.save(updated);
    }
}
