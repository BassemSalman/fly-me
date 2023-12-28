package com.kobi.flyme.service;

import com.kobi.flyme.customRepository.AirportCustomRepository;
import com.kobi.flyme.model.Airport;
import com.kobi.flyme.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService implements AirportCustomRepository {
    @Autowired
    private AirportRepository repo;
    public Airport save(Airport airport){
        return repo.save(airport);
    }

    public List<Airport> findAll(){
        return repo.findAll();
    }

    public Airport findById(int id){
        return repo.findById(id);
    }

    public boolean deleteById(int id){
        if(repo.findById(id) == null) return false;
        repo.deleteById(id);
        return repo.findById(id) == null;
    }

    public Airport update(int id, Airport updated){
        Airport toUpdate = repo.findById(id);
        if(toUpdate != null) {
            if(updated.getName() != null) toUpdate.setName(updated.getName());
            return repo.save(toUpdate);
        }
        return repo.save(updated);
    }

}
