package org.incode.domainapp.extended.module.fixtures.per_cpt.examples.document.dom.applicability.rmf;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import org.isisaddons.module.xdocreport.dom.service.XDocReportModel;

import org.incode.domainapp.extended.module.fixtures.shared.demowithurl.dom.DemoObjectWithUrl;
import org.incode.example.document.dom.impl.applicability.RendererModelFactoryAbstract;
import org.incode.example.document.dom.impl.docs.DocumentTemplate;

import lombok.Getter;

public class XDocReportModelOfDemoObject extends RendererModelFactoryAbstract<DemoObjectWithUrl> {

    public XDocReportModelOfDemoObject() {
        super(DemoObjectWithUrl.class);
    }

    @Override
    protected Object doNewRendererModel(
            final DocumentTemplate documentTemplate, final DemoObjectWithUrl demoObject) {
        return new DataModel(demoObject);
    }

    public static class DataModel implements XDocReportModel {

        // for freemarker
        @Getter
        private final DemoObjectWithUrl demoObject;

        public DataModel(final DemoObjectWithUrl demoObject) {
            this.demoObject = demoObject;
        }

        // for XDocReport
        @Override
        public Map<String, Data> getContextData() {
            return ImmutableMap.of("demoObject", Data.object(demoObject));
        }

    }
}
