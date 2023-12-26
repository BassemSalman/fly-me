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


@Table(name = "airway")
public class Airway {
    @Id
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "profit")
    private float profit;

    @OneToMany(mappedBy = "airway", cascade = CascadeType.ALL)
    private List<Plane> plane;

    @OneToMany(mappedBy = "airway", cascade = CascadeType.ALL)
    private List<Flight> flights;

}
