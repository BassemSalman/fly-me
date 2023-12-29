package com.kobi.flyme.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReservationRequest {
    @NotNull
    Integer flightId;
    @NotNull
    Integer passengerId;
}
