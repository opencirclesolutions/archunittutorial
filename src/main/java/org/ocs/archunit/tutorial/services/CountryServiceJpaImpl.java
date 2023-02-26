package org.ocs.archunit.tutorial.services;

import lombok.RequiredArgsConstructor;
import org.ocs.archunit.tutorial.dataaccess.CountryDataAccess;
import org.ocs.archunit.tutorial.domain.Country;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceJpaImpl implements CountryService{

    private final CountryDataAccess repository;

    @Override
    public Optional<Country> findByName(String name) {
        return repository.findByName(name);
    }
}
