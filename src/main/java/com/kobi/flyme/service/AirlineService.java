package com.kobi.flyme.service;

import com.kobi.flyme.customRepository.AirlineCustomRepository;
import com.kobi.flyme.model.Airline;
import com.kobi.flyme.repository.AirlineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService implements AirlineCustomRepository {
    @Autowired
    private AirlineRepository repo;
    public Airline save(Airline airline){
        return repo.save(airline);
    }

    public List<Airline> findAll(){
        return repo.findAll();
    }

    public Airline findById(int id){
        return repo.findById(id);
    }

    public boolean deleteById(int id){
        if(repo.findById(id) == null) return false;
        repo.deleteById(id);
        return repo.findById(id) == null;
    }

    public Airline increaseProfit(Airline airline, float amount) {
        airline.decreaseProfit(amount);
        return repo.save(airline);
    }

    public Airline increaseProfit(int airlineId, float amount) {
        Airline airline = repo.findById(airlineId);
        if(airline == null) return null;
        airline.decreaseProfit(amount);
        return repo.save(airline);
    }

    public Airline decreaseProfit(Airline airline, float amount) {
        airline.decreaseProfit(amount);
        return repo.save(airline);
    }

    public Airline decreaseProfit(int airlineId, float amount) {
        Airline airline = repo.findById(airlineId);
        if(airline == null) return null;
        airline.decreaseProfit(amount);
        return repo.save(airline);
    }

    // Allowed attributes: name
    public Airline update(int id, Airline updated){
        Airline toUpdate = repo.findById(id);
        if(toUpdate != null) {
            if(updated.getName() != null) toUpdate.setName(updated.getName());
            return repo.save(toUpdate);
        }
        return repo.save(updated);
    }

    public float findProfitById(int id){
        Airline airline = repo.findById(id);
        if(airline == null){
            throw new EntityNotFoundException();
        }
        return airline.getProfit();
    }
}
