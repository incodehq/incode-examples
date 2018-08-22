package org.incode.examplesapp.appdefn;

import java.util.List;

import org.apache.isis.applib.AppManifestAbstract2;
import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.incode.examplesapp.appdefn.seed.security.SeedSuperAdministratorRoleAndSvenSuperUser;

public class ExamplesAppAppManifest extends AppManifestAbstract2 {

    public ExamplesAppAppManifest() {
        super(Builder.forModule(new ExamplesAppAppDefnModule()));
    }

    @Override
    protected void overrideFixtures(
            final List<Class<? extends FixtureScript>> fixtureScriptClasses) {
        fixtureScriptClasses.add(SeedSuperAdministratorRoleAndSvenSuperUser.class);
    }
}
