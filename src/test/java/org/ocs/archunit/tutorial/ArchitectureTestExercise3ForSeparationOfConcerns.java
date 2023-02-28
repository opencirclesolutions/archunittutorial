package org.ocs.archunit.tutorial;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "org.ocs.archunit.tutorial")
public class ArchitectureTestExercise3ForSeparationOfConcerns {

    @ArchTest
    public static final ArchRule classes_in_package_dataaccess_may_not_access_classes_in_package_services =
        noClasses()
            .that().resideInAPackage("..dataaccess..")
            .should().accessClassesThat().resideInAPackage("..services..")
            .orShould().accessClassesThat().resideInAPackage("..ui..");

    @ArchTest
    public static final ArchRule classes_in_package_ui_may_not_access_classes_in_package_dataaccess =
        noClasses()
            .that().resideInAPackage("..ui..")
            .should().accessClassesThat().resideInAPackage("..dataaccess..");

    /**
     * The previous architecture rules also can be replaced by the layered architecture rule as follows:
     */
    @ArchTest
    public static final ArchRule classes_should_conform_to_a_layered_architecture =
        layeredArchitecture()
            .consideringAllDependencies()
            .layer("User interface").definedBy("..ui..")
            .layer("Service").definedBy("..services..")
            .layer("Persistence").definedBy("..dataaccess..")

            .whereLayer("User interface").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("User interface")
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service");
}
