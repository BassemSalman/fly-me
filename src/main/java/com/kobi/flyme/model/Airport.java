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


@Table(name = "airport")
public class Airport {
    @Id
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "sourceAirport", cascade = CascadeType.ALL)
    private List<Flight> flightsFrom;

    @OneToMany(mappedBy = "destinationAirport", cascade = CascadeType.ALL)
    private List<Flight> flightsTo;

}
