package org.incode.module.alias.fixture.dom.aliastype;

import java.util.Arrays;
import java.util.Collection;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;

import org.incode.module.alias.dom.spi.aliastype.AliasType;
import org.incode.module.alias.dom.spi.aliastype.AliasTypeRepository;

/**
 * Mandatory implementation of the {@link AliasTypeRepository} SPI.
 */
@DomainService(
    nature = NatureOfService.DOMAIN
)
public class AliasTypeDemoEnumRepository implements AliasTypeRepository {

    @Override
    public Collection<AliasType> aliasTypesFor(
            final Object aliased, final String atPath) {
        final AliasTypeDemoEnum[] values = AliasTypeDemoEnum.values();
        return Lists.newArrayList(
                FluentIterable.from(Arrays.asList(values))
                .transform(x -> new AliasTypeViewModel(x))
        );
    }
}
