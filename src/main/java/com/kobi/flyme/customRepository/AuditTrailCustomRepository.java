package com.kobi.flyme.customRepository;

import com.kobi.flyme.model.AuditTrail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditTrailCustomRepository  {
    List<AuditTrail> findAll();
    AuditTrail save(String description);
}
