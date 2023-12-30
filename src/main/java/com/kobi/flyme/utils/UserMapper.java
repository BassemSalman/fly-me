package com.kobi.flyme.utils;

import com.kobi.flyme.DTO.RegisterUserDTO;
import com.kobi.flyme.DTO.UserDTO;
import com.kobi.flyme.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@SuppressWarnings("UnmappedTargetProperties")
@Component
@Mapper
public interface UserMapper {

    UserDTO userToUserDTO(User user); // for responses

    @Mapping(target = "role", constant = "CLIENT")
    @Mapping(target = "resetPass", expression = "java(true)")
    @Mapping(target = "adminAirline", expression = "java(null)")
    User registerUserDTOToClient(RegisterUserDTO dto);

    @Mapping(target = "role", constant = "ADMIN")
    @Mapping(target = "resetPass", expression = "java(true)")
    User registerUserDTOToAdmin(RegisterUserDTO dto);


}

