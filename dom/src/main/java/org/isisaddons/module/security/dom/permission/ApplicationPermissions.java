/*
 *  Copyright 2014 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.isisaddons.module.security.dom.permission;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.Identifier;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.RestrictTo;
import org.apache.isis.applib.annotation.SemanticsOf;

import org.isisaddons.module.security.SecurityModule;
import org.isisaddons.module.security.dom.feature.ApplicationFeatureId;
import org.isisaddons.module.security.dom.feature.ApplicationFeatureType;
import org.isisaddons.module.security.dom.role.ApplicationRole;
import org.isisaddons.module.security.dom.user.ApplicationUser;

/**
 * @deprecated - use {@link ApplicationPermissionRepository} or {@link ApplicationPermissionMenu}.
 */
@Deprecated
public class ApplicationPermissions {

    //region > domain event classes

    public static abstract class PropertyDomainEvent<T> extends SecurityModule.PropertyDomainEvent<ApplicationPermissions, T> {
        public PropertyDomainEvent(final ApplicationPermissions source, final Identifier identifier) {
            super(source, identifier);
        }

        public PropertyDomainEvent(final ApplicationPermissions source, final Identifier identifier, final T oldValue, final T newValue) {
            super(source, identifier, oldValue, newValue);
        }
    }

    public static abstract class CollectionDomainEvent<T> extends SecurityModule.CollectionDomainEvent<ApplicationPermissions, T> {
        public CollectionDomainEvent(final ApplicationPermissions source, final Identifier identifier, final Of of) {
            super(source, identifier, of);
        }

        public CollectionDomainEvent(final ApplicationPermissions source, final Identifier identifier, final Of of, final T value) {
            super(source, identifier, of, value);
        }
    }

    public static abstract class ActionDomainEvent extends SecurityModule.ActionDomainEvent<ApplicationPermissions> {
        public ActionDomainEvent(final ApplicationPermissions source, final Identifier identifier) {
            super(source, identifier);
        }

        public ActionDomainEvent(final ApplicationPermissions source, final Identifier identifier, final Object... arguments) {
            super(source, identifier, arguments);
        }

        public ActionDomainEvent(final ApplicationPermissions source, final Identifier identifier, final List<Object> arguments) {
            super(source, identifier, arguments);
        }
    }
    //endregion

    //region > iconName

    public String iconName() {
        return "applicationPermission";
    }

    //endregion

    //region > findByRole (programmatic)

    /**
     * @deprecated - use {@link ApplicationPermissionRepository#findByRole(ApplicationRole)} instead.
     */
    @Deprecated
    @Programmatic
    public List<ApplicationPermission> findByRole(final ApplicationRole role) {
        return applicationPermissionRepository.findByRole(role);
    }
    //endregion

    //region > findByUser (programmatic)

    /**
     * @deprecated - use {@link ApplicationPermissionRepository#findByUser(ApplicationUser)} instead.
     */
    @Deprecated
    @Programmatic
    public List<ApplicationPermission> findByUser(final ApplicationUser user) {
        return applicationPermissionRepository.findByUser(user);
    }

    //endregion

    //region > findByUserAndPermissionValue (programmatic)
    /**
     * @deprecated - use {@link ApplicationPermissionRepository#findByUserAndPermissionValue(String, ApplicationPermissionValue)} instead.
     */
    @Deprecated
    @Programmatic
    public ApplicationPermission findByUserAndPermissionValue(final String username, final ApplicationPermissionValue permissionValue) {
        return applicationPermissionRepository.findByUserAndPermissionValue(username, permissionValue);
    }
    //endregion

    //region > findByRoleAndRuleAndFeatureType (programmatic)

    /**
     * @deprecated - use {@link ApplicationPermissionRepository#findByRoleAndRuleAndFeatureType(ApplicationRole, ApplicationPermissionRule, ApplicationFeatureType)} instead.
     */
    @Deprecated
    @Programmatic
    public List<ApplicationPermission> findByRoleAndRuleAndFeatureType(
            final ApplicationRole role, final ApplicationPermissionRule rule,
            final ApplicationFeatureType type) {
        return applicationPermissionRepository.findByRoleAndRuleAndFeatureType(role, rule, type);
    }
    //endregion

    //region > findByRoleAndRuleAndFeature (programmatic)

    /**
     * @deprecated - use {@link ApplicationPermissionRepository#findByRoleAndRuleAndFeature(ApplicationRole, ApplicationPermissionRule, ApplicationFeatureType, String)} instead.
     */
    @Deprecated
    @Programmatic
    public ApplicationPermission findByRoleAndRuleAndFeature(final ApplicationRole role, final ApplicationPermissionRule rule, final ApplicationFeatureType type, final String featureFqn) {
        return applicationPermissionRepository.findByRoleAndRuleAndFeature(role, rule, type, featureFqn);
    }
    //endregion

    //region > findByFeature (programmatic)

    /**
     * @deprecated - use {@link ApplicationPermissionRepository#findByFeature(ApplicationFeatureId)} instead.
     */
    @Deprecated
    @Programmatic
    public List<ApplicationPermission> findByFeature(final ApplicationFeatureId featureId) {
        return applicationPermissionRepository.findByFeature(featureId);
    }
    //endregion

    //region > newPermission (programmatic)

    /**
     * @deprecated - use {@link ApplicationPermissionRepository#newPermission(ApplicationRole, ApplicationPermissionRule, ApplicationPermissionMode, ApplicationFeatureType, String)} instead.
     */
    @Deprecated
    @Programmatic
    public ApplicationPermission newPermission(
            final ApplicationRole role,
            final ApplicationPermissionRule rule,
            final ApplicationPermissionMode mode,
            final ApplicationFeatureType featureType,
            final String featureFqn) {
        return applicationPermissionRepository.newPermission(role, rule, mode, featureType, featureFqn);
    }

    /**
     * @deprecated - use {@link ApplicationPermissionRepository#newPermissionNoCheck(ApplicationRole, ApplicationPermissionRule, ApplicationPermissionMode, ApplicationFeatureType, String)} instead.
     */
    @Deprecated
    @Programmatic
    public ApplicationPermission newPermissionNoCheck(
            final ApplicationRole role,
            final ApplicationPermissionRule rule,
            final ApplicationPermissionMode mode,
            final ApplicationFeatureType featureType,
            final String featureFqn) {
        return applicationPermissionRepository.newPermissionNoCheck(role, rule, mode, featureType, featureFqn);
    }

    /**
     * @deprecated - use {@link ApplicationPermissionRepository#newPermission(ApplicationRole, ApplicationPermissionRule, ApplicationPermissionMode, String, String, String)} instead.
     */
    @Deprecated
    @Programmatic
    public ApplicationPermission newPermission(
            final ApplicationRole role,
            final ApplicationPermissionRule rule,
            final ApplicationPermissionMode mode,
            final String featurePackage,
            final String featureClassName,
            final String featureMemberName) {
        return applicationPermissionRepository.newPermission(role, rule, mode, featurePackage, featureClassName, featureMemberName);
    }
    //endregion

    //region > allPermission (action)
    public static class AllPermissionsDomainEvent extends ActionDomainEvent {
        public AllPermissionsDomainEvent(final ApplicationPermissions source, final Identifier identifier, final Object... args) {
            super(source, identifier, args);
        }
    }

    @Action(
            domainEvent=AllPermissionsDomainEvent.class,
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            cssClassFa = "fa-list"
    )
    @MemberOrder(sequence = "100.50.1")
    public List<ApplicationPermission> allPermissions() {
        return applicationPermissionRepository.allPermissions();
    }
    //endregion

    //region  > injected
    @Inject
    ApplicationPermissionRepository applicationPermissionRepository;
    //endregion
}
