package org.incode.domainapp.extended.app.modules;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.AppManifestAbstract;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.Nature;

import org.isisaddons.module.security.dom.password.PasswordEncryptionServiceUsingJBcrypt;
import org.isisaddons.module.security.dom.permission.PermissionsEvaluationServiceAllowBeatsVeto;
import org.isisaddons.module.stringinterpolator.StringInterpolatorModule;

import org.incode.domainapp.extended.appdefn.DomainAppAppManifestAbstract;
import org.incode.domainapp.extended.appdefn.seed.security.SeedSuperAdministratorRoleAndSvenSuperUser;
import org.incode.domainapp.extended.module.fixtures.per_cpt.lib.stringinterpolator.FixturesModuleLibStringInterpolatorSubmodule;
import org.incode.domainapp.extended.module.fixtures.shared.reminder.dom.DemoReminder;
import org.incode.domainapp.extended.module.fixtures.shared.reminder.dom.DemoReminderMenu;
import org.incode.domainapp.extended.module.fixtures.shared.reminder.fixture.DemoReminder_create4;

public class ExampleDomLibStringInterpolatorAppManifest extends AppManifestAbstract {

    public static final Builder BUILDER = DomainAppAppManifestAbstract.BUILDER.withAdditionalModules(

            DemoReminder.class,
            FixturesModuleLibStringInterpolatorSubmodule.class,
            StringInterpolatorModule.class
        )
        .withFixtureScripts(
                DemoReminder_create4.class,
                SeedSuperAdministratorRoleAndSvenSuperUser.class
        )
        .withAdditionalServices(
                HomePageProvider.class,
                // necessary because of ISIS-1710
                PasswordEncryptionServiceUsingJBcrypt.class,
                PermissionsEvaluationServiceAllowBeatsVeto.class
        )
        .withConfigurationProperty("isis.website", "http://isis.apache.org");

    public ExampleDomLibStringInterpolatorAppManifest() {
        super(BUILDER);
    }

    @DomainObject(
            objectType = "HomePageProvider" // bit of a hack; this is a (manually registered) service actually
    )
    public static class HomePageProvider {

        @HomePage
        public HomePageViewModel homePage() {
            return new HomePageViewModel();
        }
    }

    @DomainObject(
            nature = Nature.VIEW_MODEL,
            objectType = "HomePageViewModel"
    )
    public static class HomePageViewModel {

        public String title() { return "Home page"; }

        @CollectionLayout(defaultView = "table")
        public List<DemoReminder> getDemoReminders() {
            return demoReminderMenu.listAllReminders();
        }

        @Inject
        DemoReminderMenu demoReminderMenu;

    }

}
