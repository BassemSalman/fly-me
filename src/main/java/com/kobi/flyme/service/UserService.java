package com.kobi.flyme.service;

import com.kobi.flyme.model.User;
import com.kobi.flyme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public User save(User user) {
        return repo.save(user);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public boolean deleteById(int id) {
        if(repo.findById(id) == null) return false;
        repo.deleteById(id);
        return repo.findById(id) == null;
    }

}
