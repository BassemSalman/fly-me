package com.kobi.flyme.DTO;

import com.kobi.flyme.model.Airline;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
// /{airlineId} in controller for client
public class RegisterUserDTO {
    @NotNull
    String username;
    @NotNull
    String password;
    Airline airline;
}
