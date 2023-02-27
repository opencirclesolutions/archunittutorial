package org.ocs.archunit.tutorial;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "org.ocs.archunit.tutorial")
public class ArchitectureTestExercise2ForStructure {

    @ArchTest
    public static final ArchRule entities_should_reside_within_package_domain =
        classes()
                .that().areAnnotatedWith(Entity.class)
                .should().resideInAPackage("..domain");

    @ArchTest
    public static final ArchRule repositories_should_reside_in_package_dataaccess =
            classes()
                    .that().areAnnotatedWith(Repository.class)
                    .or().areAssignableTo(CrudRepository.class)
                    .or().haveNameMatching(".*Repository.*")
                    .should().resideInAPackage("..dataaccess");

    @ArchTest
    public static final ArchRule services_should_reside_in_package_services =
            classes()
                    .that().areAnnotatedWith(Service.class)
                    .or().areAssignableTo(JavaClass.Predicates.simpleNameContaining("Service"))
                    .or().haveNameMatching(".*Service.*")
                    .should().resideInAPackage("..services");
}
