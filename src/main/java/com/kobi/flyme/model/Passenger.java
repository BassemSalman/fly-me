package com.kobi.flyme.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Table(name = "passenger")
public class Passenger {
    @Id
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private float balance;

    @Column(name = "booked")
    private boolean booked;

    @ManyToMany
    @JoinTable(name = "flight_passenger",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"),
            uniqueConstraints = @UniqueConstraint(
            columnNames = { "flight_id", "passenger_id" }
    ))
    private List<Flight> flights;
}
