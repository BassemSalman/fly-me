package com.kobi.flyme.repository;

import com.kobi.flyme.model.AuditTrail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditTrailRepository extends JpaRepository<AuditTrail, Integer> {
    List<AuditTrail> findAll();
    AuditTrail save(AuditTrail auditTrail);
}
