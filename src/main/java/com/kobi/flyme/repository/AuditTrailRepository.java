package com.kobi.flyme.repository;

import com.kobi.flyme.model.AuditTrail;
import com.kobi.flyme.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface AuditTrailRepository extends CrudRepository<AuditTrail, Integer> {
}
