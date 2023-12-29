package com.kobi.flyme.service;

import com.kobi.flyme.customRepository.*;
import com.kobi.flyme.model.Airline;
import com.kobi.flyme.model.Flight;
import com.kobi.flyme.model.Passenger;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
    Higher level service to handle booking, unbooking, flight cancellation, and discard passenger with refund and etc
       => where multiple repo's are needed
 */

@Service
public class ReservationService implements ReservationCustomRepository {
    @Autowired
    FlightCustomRepository flightRepo;
    @Autowired
    PassengerCustomRepository passengerRepo;
    @Autowired
    PlaneCustomRepository planeRepo;
    @Autowired
    AuditTrailCustomRepository auditRepo;
    @Autowired
    AirlineCustomRepository airlineRepo;

    private void addPassengerToFlight(Flight flight, Passenger passenger) {
        if(passenger == null || flight == null) return;
        flight.getFlightPassengers().add(passenger);
//        flight.adjustPassengerCountAndIsFull();
        flightRepo.save(flight);
    }
    private void removePassengerFromFlight(Flight flight, Passenger passenger) {
        if(passenger == null || flight == null) return;
        flight.getFlightPassengers().remove(passenger);
//        flight.adjustPassengerCountAndIsFull();
        flightRepo.save(flight);
    }

    @Transactional
    public boolean discardPassenger(int passengerId) {
        Passenger passenger = passengerRepo.findById(passengerId);
        if(passenger == null) return false;
        List<Flight> bookedFlights = flightRepo.findAllInFutureByPassengerId(passengerId);

        // can create logic to put everthing to edit it hashmaps and loop on them in the end to persist at once
        // or can call service methods in each iteration -> connection overhead

        // due to cascade, deletion will remove passenger from the passengerList automatically
        // adjust count to size of the list to be editted kel ma ttghyr l list
        // we need to adjust

        for(Flight flight : bookedFlights){
            this.removePassengerFromFlight(flight, passenger);
            airlineRepo.decreaseProfit(flight.getFlightAirline(), flight.getTicketPrice());
        }
        passengerRepo.deleteById(passengerId);
        return passengerRepo.findById(passengerId) == null;
    }

    @Transactional
    public boolean cancelFlight(int flightId) {
        Flight flight = flightRepo.findById(flightId);
        if(flight == null) return false;
        List<Passenger> passengers = flight.getFlightPassengers();
//        planeRepo.unbookPlane(flight.getFlightPlane());
        airlineRepo.decreaseProfit(flight.getFlightAirline(), flight.getTicketPrice() * passengers.size());

        for(Passenger passenger : passengers) {
            passengerRepo.refundTicketPrice(passenger, flight.getTicketPrice());
        }
        flightRepo.deleteById(flightId);
        return flightRepo.findById(flightId) == null;
    }

    @Transactional
    public boolean bookFlight(int passengerId, int flightId){
        Flight flight = flightRepo.findById(flightId);
        Passenger passenger = passengerRepo.findById(passengerId);

        if(passenger == null || flight == null){
            auditRepo.save("Either passenger with id " + passengerId + " or book flight with id " + flightId + " could not be found!");
            return false;
        }
        Airline airline = flight.getFlightAirline();
        int airlineId = airline.getId();
        float ticketPrice = flight.getTicketPrice();


        if(!flight.isFull() && passenger.canAfford(ticketPrice)){
            this.addPassengerToFlight(flight, passenger);
            passengerRepo.payTicketPrice(passenger, ticketPrice);
            airlineRepo.increaseProfit(airline, ticketPrice);
            auditRepo.save("Passenger with id " + passengerId + " successfully booked flight with id " + flightId);
            return true;
        }
        auditRepo.save("Passenger with id " + passengerId + " failed to unbook flight with id " + flightId);
        return false;
    }

    @Transactional
    public boolean unbookFlight(int passengerId, int flightId){
        Flight flight = flightRepo.findById(flightId);
        Passenger passenger = passengerRepo.findById(passengerId);

        if(passenger == null || flight == null){
            auditRepo.save("Either passenger with id " + passengerId + " or book flight with id " + flightId + " could not be found!");
            return false;
        }

                Airline airline = flight.getFlightAirline();
        int airlineId = airline.getId();
        float ticketPrice = flight.getTicketPrice();

        passengerRepo.refundTicketPrice(passenger, ticketPrice);
        this.removePassengerFromFlight(flight, passenger);
        airlineRepo.decreaseProfit(airline, ticketPrice);
        auditRepo.save("Passenger with id " + passengerId + " successfully unbooked flight with id " + flightId);
        return true;
    }


}
