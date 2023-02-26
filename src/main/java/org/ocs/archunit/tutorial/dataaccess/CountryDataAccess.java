package org.ocs.archunit.tutorial.dataaccess;

import org.ocs.archunit.tutorial.domain.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CountryDataAccess extends CrudRepository<Country, Long> {

    Optional<Country> findByName(final String name);
}
