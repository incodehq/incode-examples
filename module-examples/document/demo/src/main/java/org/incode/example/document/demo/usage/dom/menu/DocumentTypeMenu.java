package org.incode.example.document.demo.usage.dom.menu;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;

import org.incode.example.document.dom.impl.types.DocumentType;
import org.incode.example.document.dom.impl.types.DocumentTypeRepository;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "doocumentDemoUsage.DocumentTypeMenu"
)
@DomainServiceLayout(
        named = "Document Types",
        menuBar = DomainServiceLayout.MenuBar.PRIMARY,
        menuOrder = "900")
public class DocumentTypeMenu {

    @Action(semantics = SemanticsOf.SAFE)
    @MemberOrder(sequence = "2")
    public List<DocumentType> allDocumentTypes() {
        return documentTypeRepository.allTypes();
    }

    @Inject
    private DocumentTypeRepository documentTypeRepository;


}
