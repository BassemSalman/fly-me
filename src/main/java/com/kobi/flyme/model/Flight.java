package com.kobi.flyme.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Component

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// identityInfoPlaceHolder
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "passenger_count")
    private int passengerCount;

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

    @Column(name = "is_available")
    private boolean available = true; // depending on capacity - managed in service

    public void increasePassengerCount(){
        passengerCount++;
    }
    public void decreasePassengerCount(){
        passengerCount--;
    }
    public void adjustPassengerCount(){ passengerCount = flightPassengers.size(); }
    public boolean isInFuture(){ return this.date.isAfter(LocalDateTime.now()); }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.getId();
    }


}
