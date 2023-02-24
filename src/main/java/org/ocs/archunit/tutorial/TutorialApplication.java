package org.ocs.archunit.tutorial;

import org.ocs.archunit.tutorial.domain.Country;
import org.ocs.archunit.tutorial.portsadapters.CountryRepository;
import org.ocs.archunit.tutorial.portsadapters.CountryServiceJpaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class TutorialApplication {

	private static final Logger log = LoggerFactory.getLogger(TutorialApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TutorialApplication.class, args);
	}

	@Bean
	public CommandLineRunner tutorial(ApplicationContext applicationContext, CountryRepository repository, CountryServiceJpaImpl countryServiceJpa) {
		return (args) -> {
			repository.save(new Country("NL","NLD","Nederland"));
			repository.save(new Country("BE","BEL","Belgie"));
			repository.save(new Country("DE","DEU","Duitsland"));

			log.info(">>> Saved {} countries.",repository.count());

			log.info(">>> CountryRepository.findByName('Nederland') = {}",repository.findByName("Nederland"));
			log.info(">>> CountryServiceJpaImpl.findByName('Nederland') = {}",countryServiceJpa.findByName("Nederland"));

			log.info(">>> Beans: {}",applicationContext.getBeanDefinitionNames());
		};
	}
}
