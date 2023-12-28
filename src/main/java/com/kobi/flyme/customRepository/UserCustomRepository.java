package com.kobi.flyme.customRepository;

import com.kobi.flyme.model.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCustomRepository  {
    public User findByUsername(String username);
    User findById(int id);
    List<User> findAll();
    User save(@Valid User user);
    boolean deleteById(int id);

}
