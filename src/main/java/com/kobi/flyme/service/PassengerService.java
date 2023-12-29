package com.kobi.flyme.service;

import com.kobi.flyme.customRepository.PassengerCustomRepository;
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
    private AuditTrailService auditTrailService;
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
        Passenger passenger = repo.findById(id);
        if(passenger == null) return false;
        repo.deleteById(id);
        auditTrailService.save("Passenger with id " + passenger.getId() + " - attempt to delete");
        return repo.findById(id) == null;
    }

    // Allowed attributes: msisdn, email, name
    public Passenger update(int id, Passenger updated){
        Passenger toUpdate = repo.findById(id);
        if(toUpdate != null) {
            if(updated.getName() != null) toUpdate.setName(updated.getName());
            if(updated.getMsisdn() != null) toUpdate.setMsisdn(updated.getMsisdn());
            if(updated.getEmail() != null) toUpdate.setEmail(updated.getEmail());
            return repo.save(toUpdate);
        }
        return repo.save(updated);
    }

    /*
            Bad approach : modify models then perform partial update
            update(passengerId, passenger);
            airlineService.update(airlineId, airline);
            flightService.update(flightId, flight);

            Better approach : use custom methods to restrict api updating unwanted attributes
     */

    public Passenger topUp(int passengerId, float amount) {
        Passenger passenger = findById(passengerId);
        if(passenger == null) return null;
        passenger.topUp(amount);
        auditTrailService.save("Passenger with id " + passengerId + " successfully topped up " + amount + "$");
        return repo.save(passenger);
    }

    public Passenger topUp(Passenger passenger, float amount) {
        if(passenger == null) return null;
        passenger.topUp(amount);
        auditTrailService.save("Passenger with id " + passenger.getId() + " successfully topped up " + amount + "$");
        return repo.save(passenger);
    }

    public Passenger refundTicketPrice(int passengerId, float amount) {
        Passenger passenger = repo.findById(passengerId);
        if(passenger == null) return null;
        passenger.refundTicketPrice(amount);
        auditTrailService.save("Passenger with id " + passengerId + " successfully refunded " + amount + "$");
        return repo.save(passenger);
    }

    public Passenger refundTicketPrice(Passenger passenger, float amount) {
        if(passenger == null) return null;
        passenger.refundTicketPrice(amount);
        auditTrailService.save("Passenger with id " + passenger.getId() + " successfully refunded " + amount + "$");
        return repo.save(passenger);
    }

    public Passenger payTicketPrice(int passengerId, float amount) {
        Passenger passenger = repo.findById(passengerId);
        if(passenger == null) return null;
        passenger.payTicketPrice(amount);
        auditTrailService.save("Passenger with id " + passengerId + " successfully payed " + amount + "$");
        return repo.save(passenger);
    }

    public Passenger payTicketPrice(Passenger passenger, float amount) {
        if(passenger == null) return null;
        passenger.payTicketPrice(amount);
        auditTrailService.save("Passenger with id " + passenger.getId() + " successfully payed " + amount + "$");
        return repo.save(passenger);
    }


}
