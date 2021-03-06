package org.incode.examplesapp.appdefn.seed.security;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.isisaddons.module.security.dom.permission.ApplicationPermissionMode;
import org.isisaddons.module.security.dom.permission.ApplicationPermissionRule;
import org.isisaddons.module.security.dom.role.ApplicationRole;
import org.isisaddons.module.security.dom.role.ApplicationRoleRepository;
import org.isisaddons.module.security.dom.user.AccountType;
import org.isisaddons.module.security.dom.user.ApplicationUser;
import org.isisaddons.module.security.dom.user.ApplicationUserRepository;
import org.isisaddons.module.security.seed.scripts.AbstractRoleAndPermissionsFixtureScript;
import org.isisaddons.module.security.seed.scripts.AbstractUserAndRolesFixtureScript;
import org.isisaddons.module.security.seed.scripts.GlobalTenancy;

public class SeedSuperAdministratorRoleAndSvenSuperUser extends FixtureScript {

    @Override
    protected void execute(ExecutionContext executionContext) {

        executionContext.executeChild(this, new ExampleAppSuperAdministratorRole());
        executionContext.executeChild(this, new SvenSuperUser());

        // workaround ... in case the 'sven' user already exists...
        // (the SvenSuperUser fixture script unfortunately does not currently do an upsert)
        final ApplicationUser user = applicationUserRepository.findByUsername(SvenSuperUser.USERNAME);
        SvenSuperUser.roleNames().forEach(roleName -> {
            final ApplicationRole role = applicationRoleRepository.findByName(roleName);
            if(!user.getRoles().contains(role)) {
                user.addRole(role);
            }
        });
        user.updatePassword(SvenSuperUser.PASS);
        user.unlock();
    }


    @Inject
    ApplicationUserRepository applicationUserRepository;
    @Inject
    ApplicationRoleRepository applicationRoleRepository;

    public static class ExampleAppSuperAdministratorRole extends AbstractRoleAndPermissionsFixtureScript {

        public static final String ROLE_NAME = "example-super-admin";

        public ExampleAppSuperAdministratorRole() {
            super(ROLE_NAME, "Super administrator");
        }

        @Override
        protected void execute(FixtureScript.ExecutionContext executionContext) {
            newPackagePermissions(
                    ApplicationPermissionRule.ALLOW,
                    ApplicationPermissionMode.CHANGING,
                    "org");
        }

    }

    public static class SvenSuperUser extends AbstractUserAndRolesFixtureScript {

        public static final String USERNAME = "sven";
        public static final String PASS = "pass";

        public SvenSuperUser() {
            super(USERNAME, PASS, null,
                    GlobalTenancy.TENANCY_PATH, AccountType.LOCAL,
                    roleNames()
            );
        }
        static List<String> roleNames() {
            return Arrays.asList(
                    ExampleAppSuperAdministratorRole.ROLE_NAME
            );
        }
    }

}

