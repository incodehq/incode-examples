package org.incode.example.communications.demo.usage.spi.aa;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.incode.example.communications.demo.shared.demowithnotes.dom.CommsInvoice;
import org.incode.example.document.dom.impl.applicability.AttachmentAdvisorAbstract;
import org.incode.example.document.dom.impl.docs.Document;
import org.incode.example.document.dom.impl.docs.DocumentTemplate;
import org.incode.example.document.dom.impl.paperclips.Paperclip;
import org.incode.example.document.dom.impl.paperclips.PaperclipRepository;

public class AttachAdvsrOfDocAttachedToDemoInvWillAttachToSame extends
        AttachmentAdvisorAbstract<Document> {

    public AttachAdvsrOfDocAttachedToDemoInvWillAttachToSame() {
        super(Document.class);
    }

    @Override
    protected List<PaperclipSpec> doAdvise(
            final DocumentTemplate documentTemplate,
            final Document document,
            final Document createdDocument) {

        final List<Paperclip> paperclips = paperclipRepository.findByDocument(document);
        final Optional<CommsInvoice> demoInvoiceIfAny =
                paperclips.stream().map(Paperclip::getAttachedTo)
                        .filter(CommsInvoice.class::isInstance)
                        .map(CommsInvoice.class::cast)
                        .findFirst();

        return demoInvoiceIfAny.isPresent()
                ? Collections.singletonList(new PaperclipSpec(null, document, createdDocument))
                : Collections.emptyList();
    }

    @Inject
    PaperclipRepository paperclipRepository;


}

