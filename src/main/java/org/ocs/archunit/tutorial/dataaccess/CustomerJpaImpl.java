package org.ocs.archunit.tutorial.dataaccess;

import lombok.RequiredArgsConstructor;
import org.ocs.archunit.tutorial.domain.Country;
import org.ocs.archunit.tutorial.services.Customer;
import org.ocs.archunit.tutorial.services.CustomerRepository;
import org.ocs.archunit.tutorial.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerJpaImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Optional<Customer> findByName(final String name) {
        return repository.findByName(name);
    }

    @Override
    public Customer findOrCreate(final String name, final Boolean company, final Country countryHeadQuarter) {
        return findByName(name)
                .orElseGet(() ->
            repository.save(
                    Customer.builder()
                    .name(name)
                    .company(company)
                    .countryHeadQuarter(countryHeadQuarter)
                    .build()));
    }

    @Override
    public long count() {
        return repository.count();
    }
}
