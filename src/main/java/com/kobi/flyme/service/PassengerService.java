package com.kobi.flyme.service;

import com.kobi.flyme.customRepository.PassengerCustomRepository;
import com.kobi.flyme.model.Airline;
import com.kobi.flyme.model.Flight;
import com.kobi.flyme.model.Passenger;
import com.kobi.flyme.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService implements PassengerCustomRepository {
    @Autowired
    private PassengerRepository repo;
    @Autowired
    private AuditTrailService auditService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private AirlineService airlineService;

    public Passenger save(Passenger passenger){
        return repo.save(passenger);
    }

    public List<Passenger> findAll(){
        return repo.findAll();
    }

    public Passenger findById(int id){
        return repo.findById(id);
    }

    public boolean deleteById(int id){
        if(repo.findById(id) == null) return false;
        List<Flight> bookedFlights = flightService.findAllInFutureByPassengerId(id);

        repo.deleteById(id);
        return repo.findById(id) == null;
    }
    public Passenger update(int id, Passenger updated){
        Passenger toUpdate = repo.findById(id);
        if(toUpdate != null) {
            if(updated.getName() != null) toUpdate.setName(toUpdate.getName());
            return repo.save(toUpdate);
        }
        return repo.save(updated);
    }


    public boolean bookFlight(int passengerId, int flightId){
        Flight flight = flightService.findById(flightId);
        Passenger passenger = findById(passengerId);

        if(passenger == null || flight == null){
            auditService.save("Either passenger with id " + passengerId + " or book flight with id " + flightId + " could not be found!");
            return false;
        }
        Airline airline = flight.getFlightAirline();
        int airlineId = airline.getId();


        float ticketPrice = flight.getTicketPrice();
        if(flight.isAvailable() && passenger.canAfford(ticketPrice)){

            // Modify models then perform partial update
            passenger.payTicketPrice(ticketPrice);
            flight.increasePassengerCount();
            airline.increaseProfit(ticketPrice);

            auditService.save("Passenger with id " + passengerId + " successfully booked flight with id " + flightId);
            update(passengerId, passenger);
            airlineService.update(airlineId, airline);
            flightService.update(flightId, flight);
            return true;
        }
        auditService.save("Passenger with id " + passengerId + " failed to book flight with id " + flightId);
        return false;
    }

    public boolean unbookFlight(int passengerId, int flightId){
        return true;
    }

    public Passenger topUp(int passengerId, float amount) {
        Passenger passenger = findById(passengerId);
        if(passenger == null) return null;
        passenger.topUp(amount);
        return repo.save(passenger);
    }

}
