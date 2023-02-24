package org.ocs.archunit.tutorial.portsadapters;

import org.ocs.archunit.tutorial.domain.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Long> {

    Optional<Country> findByName(final String name);
}
