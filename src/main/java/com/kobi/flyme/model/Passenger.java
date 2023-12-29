package com.kobi.flyme.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// identityInfoPlaceHolder
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private float balance;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "msisdn")
    private String msisdn;


    // @JsonBackReference("FlightPassenger")
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "flight_passenger",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"),
            uniqueConstraints = @UniqueConstraint(
            columnNames = { "flight_id", "passenger_id" }
    ))
    private List<Flight> passengerFlights;

    public boolean canAfford(float ticketPrice){
        return ticketPrice <= balance;
    }

    public void payTicketPrice(float ticketPrice){
        balance -= ticketPrice;
    }
    public void refundTicketPrice(float ticketPrice) { balance += ticketPrice; }

    public void topUp(float amount) {
        balance += amount;
    }
}
