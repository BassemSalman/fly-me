package com.kobi.flyme.repository;

import com.kobi.flyme.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {
}
