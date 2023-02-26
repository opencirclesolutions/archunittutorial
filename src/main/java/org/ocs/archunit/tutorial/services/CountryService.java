package org.ocs.archunit.tutorial.services;

import org.ocs.archunit.tutorial.domain.Country;

import java.util.Optional;

public interface CountryService {

    Optional<Country> findByName(final String name);
}
