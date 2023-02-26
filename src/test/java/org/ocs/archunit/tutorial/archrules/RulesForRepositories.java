package org.ocs.archunit.tutorial.archrules;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchRule;
import lombok.experimental.UtilityClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@UtilityClass
public class RulesForRepositories {


    /**
     * This rule requires that finders of repos return either {@link java.util.Collection}s or
     * {@link java.util.Optional}s.
     *
     * <p>
     *     In particular, this excludes the option of returning a simple scalar, such as <code>Country</code>;
     *     they must return an <code>Optional&lt;Country&gt;</code> instead.  This forces the caller to
     *     handle the fact that the result might be empty (ie no result).
     * </p>
     *
     * <p>
     *     One exception is that methods named &quot;findOrCreate&quot;, which are allowed to return an instance
     *     rather than an optional.
     * </p>
     */
    public static ArchRule every_finder_method_in_class_implementing_CrudRepository_must_return_either_Collection_or_Optional() {
        return methods()
                .that().haveNameMatching("find.*")
                .and().haveNameNotMatching("findOrCreate.*")
                .and().areDeclaredInClassesThat().areAssignableTo(CrudRepository.class)
                .should().haveRawReturnType(eitherOptionalOrCollection());
    }

    /**
     * This rule requires that finders of repos return either {@link java.util.Collection}s or
     * {@link java.util.Optional}s.
     *
     * <p>
     *     In particular, this excludes the option of returning a simple scalar, such as <code>Country</code>;
     *     they must return an <code>Optional&lt;Country&gt;</code> instead.  This forces the caller to
     *     handle the fact that the result might be empty (ie no result).
     * </p>
     *
     * <p>
     *     One exception is that methods named &quot;findOrCreate&quot;, which are allowed to return an instance
     *     rather than an optional.
     * </p>
     */
    public static ArchRule every_finder_method_in_Service_named_Repository_must_return_either_Collection_or_Optional() {
        return methods()
                .that().haveNameMatching("find.*")
                .and().haveNameNotMatching("findOrCreate.*")
                .and().areDeclaredInClassesThat().areAnnotatedWith(Service.class)
                .and().areDeclaredInClassesThat().haveNameMatching(".*Repository")
                .should().haveRawReturnType(eitherOptionalOrCollection());
    }


    /**
     * This rule requires that finders of repos return either {@link java.util.Collection}s or
     * {@link java.util.Optional}s.
     *
     * <p>
     *     In particular, this excludes the option of returning a simple scalar, such as <code>Country</code>;
     *     they must return an <code>Optional&lt;Country&gt;</code> instead.  This forces the caller to
     *     handle the fact that the result might be empty (ie no result).
     * </p>
     *
     * <p>
     *     One exception is that methods named &quot;findOrCreate&quot;, which are allowed to return an instance
     *     rather than an optional.
     * </p>
     */
    public static ArchRule every_finder_method_in_Repository_must_return_either_Collection_or_Optional() {
        return methods()
                .that().haveNameMatching("find.*")
                .and().haveNameNotMatching("findOrCreate.*")
                .and().areDeclaredInClassesThat().areAnnotatedWith(Repository.class)
                .should().haveRawReturnType(eitherOptionalOrCollection());
    }

    public static ArchRule every_repository_should_be_properly_named(){
        return classes()
                .that().areAssignableTo(CrudRepository.class)
                .should().haveNameMatching(".*Repository");
    }

    static DescribedPredicate<JavaClass> eitherOptionalOrCollection() {
        return new DescribedPredicate<JavaClass>("either Optional or Collection") {
            @Override
            public boolean test(JavaClass input) {
                return input.isAssignableTo(java.util.Optional.class)
                        || input.isAssignableTo(java.util.Collection.class);
            }
        };
    }



}
