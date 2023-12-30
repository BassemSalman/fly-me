package com.kobi.flyme.service;

import com.kobi.flyme.DTO.RegisterUserDTO;
import com.kobi.flyme.DTO.UserDTO;
import com.kobi.flyme.customRepository.UserCustomRepository;
import com.kobi.flyme.model.Airline;
import com.kobi.flyme.model.User;
import com.kobi.flyme.repository.UserRepository;
import com.kobi.flyme.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService implements UserCustomRepository {
    @Autowired
    private UserRepository repo;

    @Autowired
    private UserMapper mapper;

    public UserDTO createNewAdmin(RegisterUserDTO registerUserDTO) {
        User user = mapper.registerUserDTOToAdmin(registerUserDTO);
        return mapper.userToUserDTO(repo.save(user));
    }

    public UserDTO createNewClient(RegisterUserDTO registerUserDTO) {
        User user = mapper.registerUserDTOToClient(registerUserDTO);
        return mapper.userToUserDTO(repo.save(user));
    }

    public List<UserDTO> findAll() {
        return mapAllUsersToDTO(repo.findAll());
    }

    public List<UserDTO> findAllAdmins() { return mapAllUsersToDTO(repo.findAllByRole("ADMIN"));}
    public List<UserDTO> findAllClients() { return mapAllUsersToDTO(repo.findAllByRole("CLIENT"));}

    public boolean deleteById(int id) { // to change smth
        UserDTO userDTO = mapper.userToUserDTO(repo.findById(id));
        if(userDTO == null) return false;
        repo.deleteById(id);
        return repo.findById(id) == null;
    }

    public boolean getIsResetPassById(int userId){
        User user = repo.findById(userId);
        if(user == null) return false;
        return user.isResetPass();
    }

    public UserDTO findByUsername(String username){
        return mapper.userToUserDTO(repo.findByUsername(username));
    }

    public UserDTO findById(int id) {return mapper.userToUserDTO(repo.findById(id)); }

    public List<UserDTO> findAllByAirline(Airline airline){
        return mapAllUsersToDTO(
                repo.findAll()
                        .stream()
                        .filter(user -> user.getAdminAirline() != null)
                        .filter(user -> user.getAdminAirline().getId() == airline.getId())
                        .collect(toList())
        );
    }

//    public List<UserDTO> findAllByAirlineId(int airlineId){ return mapAllUsersToDTO(repo.findAllByAdminAirline(airlineId)); }
    public List<UserDTO> mapAllUsersToDTO(List<User> users) {
        if(users == null) return null;
        return users
                .stream()
                .map(user -> mapper.userToUserDTO(user))
                .collect(toList());
    }
    public UserDTO resetPassword(int userId){
        User user = repo.findById(userId);
        user.setPassword(null);
        user.setResetPass(true);
        return mapper.userToUserDTO(repo.save(user));
    }
    public UserDTO resetPassword(String username){
        User user = repo.findByUsername(username);
        user.setPassword(null);
        user.setResetPass(true);
        return mapper.userToUserDTO(repo.save(user));
    }

    public boolean deleteByAdminAirline(Airline airline){
        repo.deleteByAdminAirline(airline);
        return repo.findAllByAdminAirline(airline).isEmpty();
    }
}

