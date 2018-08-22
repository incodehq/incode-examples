package org.incode.examplesapp.appdefn.fixture;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.DiscoverableFixtureScript;
import org.apache.isis.applib.services.metamodel.MetaModelService5;
import org.apache.isis.applib.services.queryresultscache.QueryResultsCache;

import org.incode.example.classification.demo.usage.fixture.DemoObjectWithAtPath_and_OtherObjectWithAtPath_create3;
import org.incode.example.communications.demo.usage.fixture.demoobjwithnote.CommsCustomer_and_CommsInvoice_create3;
import org.incode.example.communications.demo.usage.fixture.doctypes.DocumentType_and_DocumentTemplates_createSome;
import org.incode.example.country.fixture.CountriesRefData;
import org.incode.example.country.fixture.StatesRefData;
import org.incode.example.docfragment.demo.usage.fixture.DocFragCustomer_and_DocFragInvoice_and_fragments_create;
import org.incode.example.document.demo.usage.fixture.seed.RenderingStrategy_create6;
import org.incode.example.tags.demo.shared.fixture.TaggableObject_withTags_create3;

public class RecreateDemoFixtures extends DiscoverableFixtureScript {

    @Override
    protected void execute(final ExecutionContext ec) {

        ec.executeChild(this, metaModelService5.getAppManifest2().getTeardownFixture());

        ec.executeChild(this, new RenderingStrategy_create6());
        ec.executeChild(this, new DocumentType_and_DocumentTemplates_createSome());
        queryResultsCache.resetForNextTransaction();

        ec.executeChild(this, new CountriesRefData());
        ec.executeChild(this, new StatesRefData());

        ec.executeChild(this, new TaggableObject_withTags_create3());
        ec.executeChild(this, new DemoObjectWithAtPath_and_OtherObjectWithAtPath_create3());
        ec.executeChild(this, new CommsCustomer_and_CommsInvoice_create3());

        ec.executeChild(this, new DocFragCustomer_and_DocFragInvoice_and_fragments_create());

    }

    @Inject
    MetaModelService5 metaModelService5;

    @Inject
    QueryResultsCache queryResultsCache;

}
