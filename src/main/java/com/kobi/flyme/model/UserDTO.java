package com.kobi.flyme.model;

import lombok.Builder;
import org.springframework.stereotype.Component;

// User without resetPass and password
@Component
@Builder
public class UserDTO {
    private int id;
    private String username;
    private RoleEnum role;
    private Airline adminAirline;
}
