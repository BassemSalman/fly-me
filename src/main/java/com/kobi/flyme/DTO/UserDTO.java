package com.kobi.flyme.DTO;

import com.kobi.flyme.model.Airline;
import com.kobi.flyme.model.RoleEnum;
import lombok.Builder;
import lombok.Getter;

// User without resetPass and password
@Builder
@Getter
public class UserDTO {
    private int id;
    private String username;
    private RoleEnum role;
    private Airline adminAirline; // only for clients -.-
}
