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
    @Autowired
    private PlaneService planeService;
    @Autowired
    private AirlineService airlineService;
    public Flight save(Flight flight){ // only called from createFlight
        if(flight.getFlightPlane().isAvailable() == false) return null;
//        flight.getFlightPlane().setAvailable(false);
        return repo.save(flight);
    }

    public List<Flight> findAll(){
        return repo.findAll();
    }
    public Flight findById(int id){
        return repo.findById(id);
    }

    public boolean deleteById(int id){
        repo.deleteById(id);
        return repo.findById(id) == null;
    }

    public List<Flight> findAllAvailable(){ // could be enhanced using sql later
        return repo.findAll()
                .stream()
                .filter(Flight::isAvailable)
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


}
