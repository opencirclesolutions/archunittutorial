package org.ocs.archunit.tutorial.services;

import org.ocs.archunit.tutorial.domain.Country;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> findByName(String name);

    Customer findOrCreate(String name, Boolean company, Country countryHeadQuarter);

    long count();
}
