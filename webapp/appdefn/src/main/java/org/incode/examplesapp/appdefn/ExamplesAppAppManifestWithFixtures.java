package org.incode.examplesapp.appdefn;

import java.util.List;

import org.apache.isis.applib.AppManifestAbstract2;
import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.incode.examplesapp.appdefn.fixture.RecreateDemoFixtures;
import org.incode.examplesapp.appdefn.seed.security.SeedSuperAdministratorRoleAndSvenSuperUser;

public class ExamplesAppAppManifestWithFixtures extends ExamplesAppAppManifest {

    @Override
    protected void overrideFixtures(
            final List<Class<? extends FixtureScript>> fixtureScriptClasses) {
        super.overrideFixtures(fixtureScriptClasses);
        fixtureScriptClasses.add(RecreateDemoFixtures.class);
    }
}
