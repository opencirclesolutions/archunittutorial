package org.ocs.archunit.tutorial;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.ocs.archunit.tutorial.archrules.RulesForRepositories;

@AnalyzeClasses(packages = "org.ocs.archunit.tutorial")
public class ArchitectureTestSuccess {

    @ArchTest
    public static final ArchRule every_finder_method_in_class_implementing_CrudRepository_must_return_either_Collection_or_Optional =
            RulesForRepositories.every_finder_method_in_class_implementing_CrudRepository_must_return_either_Collection_or_Optional();

}
