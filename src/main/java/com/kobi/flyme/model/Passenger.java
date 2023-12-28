package com.kobi.flyme.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
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

    @Column(name = "is_booked")
    private boolean booked;

    @Column(name = "action_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime date;



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
