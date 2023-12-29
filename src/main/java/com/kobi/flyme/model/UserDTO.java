package com.kobi.flyme.model;

import lombok.Builder;
import lombok.Getter;

// User without resetPass and password
@Builder
@Getter
public class UserDTO {
    private int id;
    private String username;
    private RoleEnum role;
    private Airline adminAirline;
}
