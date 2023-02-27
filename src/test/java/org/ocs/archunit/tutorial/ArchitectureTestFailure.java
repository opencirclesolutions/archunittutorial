package org.ocs.archunit.tutorial;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "org.ocs.archunit.tutorial")
public class ArchitectureTestFailure {

    @ArchTest
    public static final ArchRule no_class_should_depend_on_hibernate =
            noClasses().should().dependOnClassesThat().resideInAPackage("org.hibernate.*");
}
