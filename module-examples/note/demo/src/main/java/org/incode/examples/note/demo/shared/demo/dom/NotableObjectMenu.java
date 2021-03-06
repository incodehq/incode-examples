package org.incode.examples.note.demo.shared.demo.dom;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        objectType = "noteDemoShared.NotableObjectMenu"
)
@DomainServiceLayout(
        named = "Demo",
        menuOrder = "10.1"
)
public class NotableObjectMenu {


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<NotableObject> listAllDemoObjects() {
        return repository.listAll();
    }


    @MemberOrder(sequence = "2")
    public NotableObject createDemoObject(final String name) {
        return repository.create(name);
    }

    @MemberOrder(sequence = "3")
    public List<NotableObject> findDemoObjectByName(final String name) {
        return repository.findByName(name);
    }

    @javax.inject.Inject
    NotableObjectRepository repository;


}
