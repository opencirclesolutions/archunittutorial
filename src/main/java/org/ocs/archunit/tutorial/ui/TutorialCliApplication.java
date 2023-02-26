package org.ocs.archunit.tutorial.ui;

import lombok.val;
import org.ocs.archunit.tutorial.domain.Country;
import org.ocs.archunit.tutorial.dataaccess.CountryDataAccess;
import org.ocs.archunit.tutorial.services.CountryServiceJpaImpl;
import org.ocs.archunit.tutorial.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.ocs.archunit.tutorial.ui.TutorialCliApplication.ARCHUNIT_TUTORIAL_BASE_PACKAGE;

@SpringBootApplication(scanBasePackages = ARCHUNIT_TUTORIAL_BASE_PACKAGE)
@EnableJpaRepositories(basePackages = ARCHUNIT_TUTORIAL_BASE_PACKAGE)
@EntityScan(basePackages = ARCHUNIT_TUTORIAL_BASE_PACKAGE)
public class TutorialCliApplication {

	private static final Logger log = LoggerFactory.getLogger(TutorialCliApplication.class);
	public static final String ARCHUNIT_TUTORIAL_BASE_PACKAGE = "org.ocs.archunit.tutorial";

	public static void main(String[] args) {
		SpringApplication.run(TutorialCliApplication.class, args);
	}

	@Bean
	public CommandLineRunner tutorial(
			ApplicationContext applicationContext,
			CountryDataAccess countryRepository,
			CountryServiceJpaImpl countryServiceJpa,
			CustomerService customerService) {

		return (args) -> {
			log.info(">>>");
			log.info(">>> STARTED ArchUnit Tutorial Cli application");
			log.info(">>>");

			val nl = countryRepository.save(new Country("NL","NLD","Nederland"));
			val be = countryRepository.save(new Country("BE","BEL","Belgie"));
			val de = countryRepository.save(new Country("DE","DEU","Duitsland"));

			log.info(">>> Saved {} countries.",countryRepository.count());

			customerService.findOrCreate("Open Circle Solutions", true, nl);
			customerService.findOrCreate("Belgian Chocolate company", true, be);
			customerService.findOrCreate("Albert Einstein", false, de);

			log.info(">>> Saved {} customers.", customerService.count());

			log.info(">>>");
			log.info(">>> CountryRepository.findByName('Nederland') = {}",countryRepository.findByName("Nederland"));
			log.info(">>> CountryServiceJpaImpl.findByName('Nederland') = {}",countryServiceJpa.findByName("Nederland"));

			log.info(">>>");
			log.info(">>> CustomerRepository.findByName('Open Circle Solutions') = {}",countryRepository.findByName("Open Circle Solutions"));
			log.info(">>> CustomerServiceJpaImpl.findByName('Albert Einstein') = {}",countryServiceJpa.findByName("Albert Einstein"));
		};
	}
}
