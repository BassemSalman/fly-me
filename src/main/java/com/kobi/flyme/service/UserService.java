package com.kobi.flyme.service;

import com.kobi.flyme.customRepository.UserCustomRepository;
import com.kobi.flyme.model.RoleEnum;
import com.kobi.flyme.model.User;
import com.kobi.flyme.model.UserDTO;
import com.kobi.flyme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserCustomRepository {
    @Autowired
    private UserRepository repo;

    public UserDTO createNewAdmin(User user) {
        user.setRole(RoleEnum.ADMIN);
        user.setResetPass(true);
        user.setAdminAirline(null);
        return mapUserToDTO(repo.save(user));
    }

    public UserDTO createNewClient(User user) {
        user.setRole(RoleEnum.CLIENT);
        user.setResetPass(true);
        return mapUserToDTO(repo.save(user));
    }

    public List<UserDTO> findAll() {
        return mapAllUsersToDTO(repo.findAll());
    }

    public boolean deleteById(int id) {
        if(repo.findById(id) == null) return false;
        repo.deleteById(id);
        return repo.findById(id) == null;
    }

    public UserDTO mapUserToDTO(User user) {
        if(user == null) return null;
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .adminAirline(user.getAdminAirline())
                .build();
    }

    public List<UserDTO> mapAllUsersToDTO(List<User> users) {
        if(users == null) return null;
        return users
                .stream()
                .map(user -> mapUserToDTO(user))
                .collect(Collectors.toList());
    }

    public boolean getIsResetPassById(int userId){
        User user = repo.findById(userId);
        if(user == null) return false;
        return user.isResetPass();
    }

    public UserDTO findByUsername(String username){
        return mapUserToDTO(repo.findByUsername(username));
    }
}
