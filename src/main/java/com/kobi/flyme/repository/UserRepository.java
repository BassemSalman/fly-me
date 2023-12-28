package com.kobi.flyme.repository;

import com.kobi.flyme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
    User findById(int id);
    List<User> findAll();
    User save(User user);
    void deleteById(int id);
}
