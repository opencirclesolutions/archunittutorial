package org.ocs.archunit.tutorial;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.ocs.archunit.tutorial.archrules.RulesForRepositories;
import org.springframework.data.repository.CrudRepository;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.assignableTo;
import static com.tngtech.archunit.core.domain.properties.HasType.Predicates.rawType;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "org.ocs.archunit.tutorial")
public class ArchitectureTestFailure {

    @ArchTest
    public static final ArchRule no_class_should_depend_on_hibernate =
            noClasses().should().dependOnClassesThat().resideInAPackage("org.hibernate.*");

    @ArchTest
    public static final ArchRule every_repository_should_be_properly_named =
            RulesForRepositories.every_repository_should_be_properly_named();

    @ArchTest
    public static final ArchRule every_service_should_be_properly_named =
            classes()
                    .that().containAnyFieldsThat(rawType(assignableTo(CrudRepository.class)))
                    .should().haveNameMatching(".*Service.*");
}
