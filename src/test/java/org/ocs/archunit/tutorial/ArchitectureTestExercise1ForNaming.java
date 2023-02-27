package org.ocs.archunit.tutorial;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.ocs.archunit.tutorial.archrules.RulesForRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.assignableTo;
import static com.tngtech.archunit.core.domain.properties.HasType.Predicates.rawType;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "org.ocs.archunit.tutorial")
public class ArchitectureTestExercise1ForNaming {

    @ArchTest
    public static final ArchRule every_repository_should_be_properly_named =
            RulesForRepositories.every_repository_should_be_properly_named();

    @ArchTest
    public static final ArchRule every_service_should_be_properly_named =
            classes()
                    .that().areAnnotatedWith(Service.class)
                    .or().containAnyFieldsThat(rawType(assignableTo(CrudRepository.class)))
                    .should().haveNameMatching(".*Service.*");
}
