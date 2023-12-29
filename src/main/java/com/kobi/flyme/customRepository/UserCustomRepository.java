package com.kobi.flyme.customRepository;

import com.kobi.flyme.model.User;
import com.kobi.flyme.model.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCustomRepository  {
   UserDTO createNewAdmin(User user);
   UserDTO createNewClient(User user);
   UserDTO findByUsername(String username);

   List<UserDTO> findAll();
   boolean deleteById(int id);
   UserDTO mapUserToDTO(User user);
   List<UserDTO> mapAllUsersToDTO(List<User> users);
   boolean getIsResetPassById(int userId);
}
