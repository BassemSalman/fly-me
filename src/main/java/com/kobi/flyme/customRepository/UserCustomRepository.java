package com.kobi.flyme.customRepository;

import com.kobi.flyme.DTO.RegisterUserDTO;
import com.kobi.flyme.DTO.UserDTO;
import com.kobi.flyme.model.Airline;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCustomRepository  {
   UserDTO createNewAdmin(RegisterUserDTO registerUserDTO);
   UserDTO createNewClient(RegisterUserDTO registerUserDTO);
   UserDTO findByUsername(String username);
   UserDTO findById(int id);
   List<UserDTO> findAll();
   boolean deleteById(int id);
   boolean getIsResetPassById(int userId);
//   List<UserDTO> findAllByAirline(Airline airline);
   UserDTO resetPassword(int userId);
   boolean deleteByAdminAirline(Airline airline);
}
