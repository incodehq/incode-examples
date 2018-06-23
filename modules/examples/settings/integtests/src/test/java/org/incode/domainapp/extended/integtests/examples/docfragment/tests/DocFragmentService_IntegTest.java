package org.incode.example.docfragment.integtests.tests;

import java.util.Locale;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.xactn.TransactionService;

import org.incode.example.docfragment.integtests.docfragment.DocFragmentModuleIntegTestAbstract;
import org.incode.example.alias.demo.examples.docfragment.fixture.DemoCustomer_and_DemoInvoiceWithAtPath_and_fragments_create;
import org.incode.examples.commchannel.demo.shared.invoicewithatpath.dom.DemoInvoiceWithAtPath;
import org.incode.examples.commchannel.demo.shared.invoicewithatpath.fixture.DemoInvoiceWithAtPathData;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class DocFragmentService_IntegTest extends DocFragmentModuleIntegTestAbstract {

    @Inject
    FixtureScripts fixtureScripts;
    @Inject
    TransactionService transactionService;

    @Inject
    ServiceRegistry2 serviceRegistry2;


    @Before
    public void setUp() throws Exception {

        // given
        fixtureScripts.runFixtureScript(new DemoCustomer_and_DemoInvoiceWithAtPath_and_fragments_create(), null);
        transactionService.nextTransaction();

        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ITALY);
    }

    Locale defaultLocale;

    @After
    public void tearDown() throws Exception {
        if(defaultLocale != null) {
            Locale.setDefault(defaultLocale);
        }
    }



    public static class Render2 extends DocFragmentService_IntegTest {

        @Test
        public void happy_case() throws Exception {
            // given
            final DemoInvoiceWithAtPath invoice1 = DemoInvoiceWithAtPathData.Invoice1.findUsing(serviceRegistry2);
            assertThat(invoice1.getRendered()).isNull();

            // when
            invoice1.render(invoice1.default0Render());

            // then
            assertThat(invoice1.getRendered()).isEqualTo("The invoice will be due on the 31-Jan-2017, payable in 30 days");
        }
    }



}