package org.ocs.archunit.tutorial.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Country {

    private String iso2code;
    private String iso3code;
    private String name;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;

    @Builder
    public Country(String iso2code, String iso3code, String name) {
        this.iso2code = iso2code;
        this.iso3code = iso3code;
        this.name = name;
    }
}
