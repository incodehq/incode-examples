package org.incode.examplesapp.appdefn.fixture;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.fixturespec.FixtureScriptsSpecification;
import org.apache.isis.applib.services.fixturespec.FixtureScriptsSpecificationProvider;

/**
 * Specifies where to find fixtures, and other settings.
 */
@DomainService(
        nature = NatureOfService.DOMAIN
)
public class ExamplesAppFixtureScriptsSpecProvider implements FixtureScriptsSpecificationProvider {

    @Override
    public FixtureScriptsSpecification getSpecification() {
        return FixtureScriptsSpecification
                .builder(ExamplesAppFixtureScriptsSpecProvider.class)
                .with(FixtureScripts.MultipleExecutionStrategy.EXECUTE)
                .withRunScriptDefault(RecreateDemoFixtures.class)
                .withRunScriptDropDown(FixtureScriptsSpecification.DropDownPolicy.CHOICES)
                .withRecreate(RecreateDemoFixtures.class)
                .build();
    }
}
