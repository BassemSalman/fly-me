package com.kobi.flyme.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReservationDTO {
    @NotNull
    Integer flightId;
    @NotNull
    Integer passengerId;
}
