package com.kobi.flyme.repository;

import com.kobi.flyme.model.Airline;
import com.kobi.flyme.model.User;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findById(int id);
    List<User> findAll();
    User save(@Valid User user);
    void deleteById(int id);
    List<User> findAllByRole(String role);
    List<User> findAllByAdminAirline(Airline airline);
    void deleteByAdminAirline(Airline airline);
}
