package org.ocs.archunit.tutorial.services;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ocs.archunit.tutorial.domain.Country;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    private String name;
    private Boolean company;
    @ManyToOne
    private Country countryHeadQuarter;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;

    @Builder
    public Customer(String name, Boolean company, Country countryHeadQuarter) {
        this.name = name;
        this.company = company;
        this.countryHeadQuarter = countryHeadQuarter;
    }
}
