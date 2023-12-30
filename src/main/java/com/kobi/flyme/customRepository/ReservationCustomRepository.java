package com.kobi.flyme.customRepository;

public interface ReservationCustomRepository {
    boolean bookFlight(int passengerId, int flightId);
    boolean unbookFlight(int passengerId, int flightId);
    boolean discardPassenger(int passengerId); // instead of passenger deletion
    boolean cancelFlight(int flightId); // instead of flight cancellation
//    void addPassengerToFlight(Flight flight, Passenger passenger);
//    void removePassengerFromFlight(Flight flight, Passenger passenger);

    boolean discardAirline(int airlineId); // should refund to all upcoming flights, thus cancel flight loop
}
