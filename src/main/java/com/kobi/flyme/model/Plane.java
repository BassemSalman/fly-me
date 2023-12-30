package com.kobi.flyme.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Table(name = "plane")
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "model")
    private String model;

    @Column(name = "capacity")
    private int capacity;

//    @Column(name = "is_available")
//    private boolean isAvailable;

    // @JsonBackReference("FlightPlane")
    @JsonIgnore
    @OneToMany(mappedBy="flightPlane", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<Flight> planeFlights;


    // @JsonBackReference("AirlinePlane")
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline planeAirline;

    // considered available in case flight has departured since 10 seconds
    public boolean isAvailable() {
        return this.planeFlights
                .stream()
                .filter(
                        flight -> flight.getDate()
                        .isBefore(LocalDateTime.now().minus(1, ChronoUnit.MINUTES))
                )
                .count() == 0;
    }
}
