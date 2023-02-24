package org.ocs.archunit.tutorial.portsadapters;

import org.ocs.archunit.tutorial.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceJpaImpl implements CountryService{

    @Autowired
    CountryRepository repository;

    @Override
    public Optional<Country> findByName(String name) {
        return repository.findByName(name);
    }
}
