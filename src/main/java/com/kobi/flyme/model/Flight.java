package com.kobi.flyme.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @Column(name = "passenger_count")
//    private int passengerCount;

    // @JsonBackReference("AirlineFlight")
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline flightAirline;

    // @JsonManagedReference("FlightPlane")
    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane flightPlane;

    // @JsonManagedReference("FlightsFrom")
    @ManyToOne
    @JoinColumn(name = "source_airport_id")
    private Airport sourceAirport;

    // @JsonManagedReference("FlightsTo")
    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destinationAirport;

    // @JsonManagedReference("FlightPassengers")
    @ManyToMany(mappedBy = "passengerFlights")
    private List<Passenger> flightPassengers;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @Column(name = "ticket_price")
    private float ticketPrice;

//    @Column(name = "is_full")
//    private boolean isFull; // depending on capacity - managed in service

//    public Flight(){
//        this.isFull=false;
//        this.flightPassengers=new ArrayList<>();
//    }

    public int passengerCount() { return this.flightPassengers.size(); }
    public boolean isFull() { return this.flightPlane.getCapacity() == this.flightPassengers.size();}
    public boolean isInFuture(){ return this.date.isAfter(LocalDateTime.now()); }
    public boolean isAvailable(){ return isInFuture() && !isFull(); }

    // call this after each delete or unbook, however upon save we need to adjust using another way since it depends on list
//    public void adjustPassengerCountAndIsFull(){
//        passengerCount = flightPassengers.size();
//        isFull = (passengerCount == flightPlane.getCapacity());
//    }

    @Override
    public int hashCode(){
        return Integer.hashCode(id);
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.getId();
    }
}
