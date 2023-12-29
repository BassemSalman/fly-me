package com.kobi.flyme.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// identityInfoPlaceHolder
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    // @JsonBackReference("FlightsFrom")
    @JsonIgnore
    @OneToMany(mappedBy = "sourceAirport", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<Flight> flightsFrom;

    // @JsonBackReference("FlightsTo")
    @JsonIgnore
    @OneToMany(mappedBy = "destinationAirport", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<Flight> flightsTo;
    
}
