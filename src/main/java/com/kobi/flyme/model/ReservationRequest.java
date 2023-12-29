package com.kobi.flyme.model;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ReservationRequest {
    @NotNull
    Integer flightId;
    @NotNull
    Integer passengerId;
}
