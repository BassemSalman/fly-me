package com.kobi.flyme.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// identityInfoPlaceHolder
@Table(name = "airline")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "profit")
    private float profit;

    // @JsonManagedReference("AirlinePlane")
    @JsonIgnore
    @OneToMany(mappedBy = "planeAirline", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<Plane> airlinePlanes;

    // @JsonBackReference("AirlineFlight")
    @JsonIgnore
    @OneToMany(mappedBy = "flightAirline", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Flight> airlineFlights;

    // @JsonBackReference("AdminAirline")
    @JsonIgnore
    @OneToMany(mappedBy = "adminAirline", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<User> airlineAdmins;


    public void increaseProfit(float ticketPrice){
        profit += ticketPrice;
    }
    public void decreaseProfit(float ticketPrice){
        profit -= ticketPrice;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(id);
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airline airline = (Airline) o;
        return id == airline.getId();
    }


}
