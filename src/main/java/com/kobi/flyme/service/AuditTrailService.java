package com.kobi.flyme.service;

import com.kobi.flyme.customRepository.AuditTrailCustomRepository;
import com.kobi.flyme.model.AuditTrail;
import com.kobi.flyme.repository.AuditTrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditTrailService implements AuditTrailCustomRepository {
    @Autowired
    private AuditTrailRepository repo;
    public AuditTrail save(String description){
        return repo.save(AuditTrail.getInstance(description));
    }

    public List<AuditTrail> findAll(){
        return repo.findAll();
    }

}
