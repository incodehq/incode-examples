package org.incode.example.document.dom.mixins;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.services.queryresultscache.QueryResultsCache;

import org.incode.example.document.DocumentModule;
import org.incode.example.document.dom.impl.docs.Document;
import org.incode.example.document.dom.impl.docs.DocumentTemplate;
import org.incode.example.document.dom.impl.paperclips.Paperclip;
import org.incode.example.document.dom.services.DocumentCreatorService;

/**
 * Create a {@link Document} and attach using a {@link Paperclip}, then render in the foreground.
 */
public abstract class T_createAndAttachDocumentAndRender<T>  {

    protected final T domainObject;

    public T_createAndAttachDocumentAndRender(final T domainObject) {
        this.domainObject = domainObject;
    }



    public static class ActionDomainEvent extends DocumentModule.ActionDomainEvent<T_createAndAttachDocumentAndRender> {}

    @Action(
            domainEvent = ActionDomainEvent.class,
            semantics = SemanticsOf.NON_IDEMPOTENT
    )
    @ActionLayout(
            contributed = Contributed.AS_ACTION
    )
    @MemberOrder(name = "documents", sequence = "3.1")
    public Object $$(final DocumentTemplate template) throws IOException {

        final Document document =
                documentCreatorService.createDocumentAndAttachPaperclips(domainObject, template);

        render(template, document);

        return document;
    }

    public boolean hide$$() {
        return choices0$$().isEmpty();
    }

    /**
     * So can be overridden...  seems to be required for integration tests (wrapper factory).
     */
    public TranslatableString disable$$() { return null; }


    public DocumentTemplate default0$$() {
        final List<DocumentTemplate> documentTemplates = choices0$$();
        return documentTemplates.size() == 1 ? documentTemplates.get(0): null;
    }

    /**
     * All templates which are applicable to the domain object's atPath, and which can be created and attached to at
     * least one domain object.
     */
    public List<DocumentTemplate> choices0$$() {
        return queryResultsCache.execute(
                () -> documentTemplateService.documentTemplatesForCreateAndAttach(domainObject),
                getClass(), "$$", domainObject);
    }


    protected void render(
            final DocumentTemplate template,
            final Document document) {
        document.render(template, domainObject);
    }

    //region > injected services

    @Inject
    DocumentTemplateForAtPathService documentTemplateService;

    @Inject
    DocumentCreatorService documentCreatorService;

    @Inject
    QueryResultsCache queryResultsCache;

    //endregion



}
