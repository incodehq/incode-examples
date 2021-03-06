package org.incode.example.communications.dom.impl;

import org.junit.Test;

import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;

import org.incode.example.communications.dom.impl.commchannel.CommunicationChannelOwner;
import org.incode.example.communications.dom.impl.commchannel.PostalAddress;

import org.incode.module.unittestsupport.dom.bean.AbstractBeanPropertiesTest;
import org.incode.module.unittestsupport.dom.bean.PojoTester;
import org.incode.example.country.dom.impl.Country;
import org.incode.example.country.dom.impl.State;

public class PostalAddress_Test {

    public static class BeanProperties extends AbstractBeanPropertiesTest {

        @Test
        public void test() {
            newPojoTester()
                    .withFixture(pojos(Country.class))
                    .withFixture(pojos(State.class))
                    .withFixture(pojos(CommunicationChannelOwner.class, CommunicationChannelOwnerForTesting.class))
                    .withFixture(pojos(ApplicationTenancy.class))
                    .exercise(new PostalAddress(), PojoTester.FilterSet.excluding("owner"));
        }
    }
}