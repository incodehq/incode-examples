/*
 *  Copyright 2016 Dan Haywood
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
package org.isisaddons.module.freemarker.dom.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.services.config.ConfigurationService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@DomainService(nature = NatureOfService.DOMAIN)
public class FreeMarkerService {

    private TemplateLoaderFromThreadLocal templateLoader;
    private Configuration cfg;

    @PostConstruct
    public void init() {
        cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setDefaultEncoding("UTF-8");

        final String deploymentType = configurationService.getProperty("isis.deploymentType");
        final boolean isPrototyping = deploymentType == null || deploymentType.contains("prototyping");
        final TemplateExceptionHandler handler = isPrototyping
                        ? TemplateExceptionHandler.HTML_DEBUG_HANDLER
                        : TemplateExceptionHandler.RETHROW_HANDLER;
        cfg.setTemplateExceptionHandler(handler);

        templateLoader = new TemplateLoaderFromThreadLocal();
        cfg.setTemplateLoader(templateLoader);

        cfg.setLogTemplateExceptions(true);
    }

    /**
     * Uses the provided document type (reference) and atPath to lookup the template's text.
     *
     * @param dataModel - will usually be a strongly-typed DTO, but a {@link Map} can also be used.
     */
    @Programmatic
    public String render(
            final String templateName,
            final String templateChars,
            final Object dataModel) throws IOException, TemplateException {
        return templateLoader
                .withTemplateSource(templateName, templateChars, new TemplateLoaderFromThreadLocal.Block() {
            @Override
            public String exec(final TemplateSource templateSource) throws IOException, TemplateException {
                final StringWriter sw = new StringWriter();
                final Template template = cfg.getTemplate(templateSource.getTemplateName());
                template.process(dataModel, sw);
                return sw.toString();
            }
        });
    }


    //region > injected services
    @Inject
    ConfigurationService configurationService;
    //endregion

}



