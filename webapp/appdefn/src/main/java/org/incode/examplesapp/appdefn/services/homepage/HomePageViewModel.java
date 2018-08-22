package org.incode.examplesapp.appdefn.services.homepage;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Nature;
import org.apache.isis.applib.services.repository.RepositoryService;

import org.incode.example.alias.demo.shared.dom.AliasedObject;
import org.incode.example.alias.demo.shared.dom.AliasedObjectRepository;
import org.incode.example.alias.demo.usage.dom.AliasForAliasedObject;
import org.incode.example.alias.dom.impl.Alias;
import org.incode.example.classification.demo.shared.demowithatpath.dom.SomeClassifiedObject;
import org.incode.example.classification.demo.shared.demowithatpath.dom.SomeClassifiedObjectMenu;
import org.incode.example.classification.demo.usage.dom.menu.TaxonomyMenu;
import org.incode.example.classification.dom.impl.applicability.Applicability;
import org.incode.example.classification.dom.impl.category.Category;
import org.incode.example.classification.dom.impl.classification.Classification;
import org.incode.example.commchannel.demo.shared.dom.CommChannelCustomer;
import org.incode.example.commchannel.demo.shared.dom.CommChannelCustomerRepository;
import org.incode.example.commchannel.dom.impl.emailaddress.EmailAddress;
import org.incode.example.commchannel.dom.impl.phoneorfax.PhoneOrFaxNumber;
import org.incode.example.commchannel.dom.impl.postaladdress.PostalAddress;
import org.incode.example.communications.demo.shared.demowithnotes.dom.CommsCustomer;
import org.incode.example.communications.demo.shared.demowithnotes.dom.CommsCustomerRepository;
import org.incode.example.communications.demo.shared.demowithnotes.dom.CommsInvoice;
import org.incode.example.communications.demo.shared.demowithnotes.dom.CommsInvoiceRepository;
import org.incode.example.communications.demo.usage.dom.commchannels.CommunicationChannelOwnerLinkForCustomer;
import org.incode.example.communications.demo.usage.dom.paperclips.PaperclipForDemoInvoice;
import org.incode.example.communications.dom.impl.comms.CommChannelRole;
import org.incode.example.communications.dom.impl.comms.Communication;
import org.incode.example.communications.dom.impl.paperclips.PaperclipForCommunication;
import org.incode.example.country.dom.impl.Country;
import org.incode.example.country.dom.impl.State;
import org.incode.example.docfragment.demo.shared.customer.dom.DocFragCustomer;
import org.incode.example.docfragment.dom.impl.DocFragment;
import org.incode.example.docfragment.dom.impl.DocFragmentRepository;
import org.incode.example.document.demo.shared.demowithnotes.dom.DocDemoObjectWithNotes;
import org.incode.example.document.demo.shared.demowithnotes.dom.DocInvoice;
import org.incode.example.document.demo.shared.demowithurl.dom.DocDemoObjectWithUrl;
import org.incode.example.document.demo.shared.other.dom.DocOtherObject;
import org.incode.example.document.demo.usage.dom.menu.DocumentTypeMenu;
import org.incode.example.document.dom.impl.docs.DocumentAbstract;
import org.incode.example.document.dom.impl.docs.DocumentRepository;
import org.incode.example.document.dom.impl.docs.DocumentTemplate;
import org.incode.example.document.dom.impl.docs.DocumentTemplateRepository;
import org.incode.example.document.dom.impl.rendering.RenderingStrategy;
import org.incode.example.document.dom.impl.rendering.RenderingStrategyRepository;
import org.incode.example.document.dom.impl.types.DocumentType;
import org.incode.example.note.dom.impl.note.Note;
import org.incode.example.note.dom.impl.note.NoteRepository;
import org.incode.example.tags.demo.shared.dom.demo.TaggableObject;
import org.incode.example.tags.demo.shared.dom.demo.TaggableObjectMenu;
import org.incode.example.tags.dom.impl.Tag;
import org.incode.examples.note.demo.shared.demo.dom.NotableObject;
import org.incode.examples.note.demo.shared.demo.dom.NotableObjectRepository;
import org.incode.examples.note.demo.usage.dom.demolink.NotableLinkForNotableObject;

@DomainObject(
        nature = Nature.VIEW_MODEL,
        objectType = "org.incode.domainapp.example.app.HomePageViewModel"
)
public class HomePageViewModel {

    public String title() {
        return "Home page";
    }


    // example.alias

    public List<AliasedObject> getExampleAliasAliasedObjects() {
        return aliasedObjectRepository.listAll();
    }

    public List<AliasForAliasedObject> getExampleAliasAliasForAliasedObjects() {
        return repositoryService.allInstances(AliasForAliasedObject.class);
    }

    public List<Alias> getExampleAliasAliases() {
        return repositoryService.allInstances(Alias.class);
    }


    @Inject
    AliasedObjectRepository aliasedObjectRepository;



    // example.classification

    public List<SomeClassifiedObject> getExampleClassificationSomeClassifiedObjects() {
        return someClassifiedObjectMenu.listAllOfSomeClassifiedObjects();
    }

    @Inject
    SomeClassifiedObjectMenu someClassifiedObjectMenu;

    public List<Category> getExampleClassificationTaxonomies() {
        return taxonomyMenu.listAllTaxonomies();
    }

    public List<Category> getExampleClassificationCategories() {
        return repositoryService.allInstances(Category.class);
    }

    public List<Applicability> getExampleClassificationApplicabilities() {
        return repositoryService.allInstances(Applicability.class);
    }

    public List<Classification> getExampleClassifications() {
        return repositoryService.allInstances(Classification.class);
    }

    @Inject
    TaxonomyMenu taxonomyMenu;



    // example.commchannel

    public List<CommChannelCustomer> getExampleCommChannelCustomers() {
        return commChannelCustomerRepository.listAll();
    }

    @Inject
    CommChannelCustomerRepository commChannelCustomerRepository;

    public List<PostalAddress> getExampleCommChannelPostalAddresses() {
        return repositoryService.allInstances(PostalAddress.class);
    }

    public List<EmailAddress> getExampleCommChannelEmailAddresses() {
        return repositoryService.allInstances(EmailAddress.class);
    }

    public List<PhoneOrFaxNumber> getExampleCommChannelPhoneOrFaxNumbers() {
        return repositoryService.allInstances(PhoneOrFaxNumber.class);
    }



    // example.communications

    public List<CommsCustomer> getExampleCommunicationsCustomers() {
        return commsCustomerRepository.listAll();
    }

    @Inject
    CommsCustomerRepository commsCustomerRepository;

    public List<CommsInvoice> getExampleCommunicationsInvoices() {
        return commsInvoiceRepository.listAll();
    }

    @Inject
    CommsInvoiceRepository commsInvoiceRepository;


    public List<CommunicationChannelOwnerLinkForCustomer> getExampleCommunicationsCommunicationChannelOwnerLinkForCustomers() {
        return repositoryService.allInstances(CommunicationChannelOwnerLinkForCustomer.class);
    }

    public List<org.incode.example.communications.dom.impl.commchannel.PostalAddress> getExampleCommunicationsPostalAddresses() {
        return repositoryService.allInstances(org.incode.example.communications.dom.impl.commchannel.PostalAddress.class);
    }

    public List<org.incode.example.communications.dom.impl.commchannel.EmailAddress> getExampleCommunicationsEmailAddresses() {
        return repositoryService.allInstances(org.incode.example.communications.dom.impl.commchannel.EmailAddress.class);
    }

    public List<org.incode.example.communications.dom.impl.commchannel.PhoneOrFaxNumber> getExampleCommunicationsPhoneOrFaxNumbers() {
        return repositoryService.allInstances(org.incode.example.communications.dom.impl.commchannel.PhoneOrFaxNumber.class);
    }

    public List<PaperclipForCommunication> getExampleCommunicationsPaperclipForCommunications() {
        return repositoryService.allInstances(PaperclipForCommunication.class);
    }

    public List<Communication> getExampleCommunications() {
        return repositoryService.allInstances(Communication.class);
    }

    public List<PaperclipForDemoInvoice> getExampleCommunicationsPaperclipForDemoInvoice() {
        return repositoryService.allInstances(PaperclipForDemoInvoice.class);
    }

    public List<CommChannelRole> getExampleCommunicationsCommChannelRoles() {
        return repositoryService.allInstances(CommChannelRole.class);
    }



    // example.country

    public List<Country> getExampleCountryCountries() {
        return repositoryService.allInstances(Country.class);
    }

    public List<State> getExampleCountryStates() {
        return repositoryService.allInstances(State.class);
    }



    // exampledocfragment

    public List<DocFragCustomer> getExampleDocFragmentCustomers() {
        return repositoryService.allInstances(DocFragCustomer.class);
    }

    public List<DocFragment> getExampleDocFragmentDocFragments() {
        return docfragmentRepository.listAll();
    }

    @javax.inject.Inject
    DocFragmentRepository docfragmentRepository;




    // example.document

    public List<DocDemoObjectWithNotes> getExampleDocumentDocDemoObjectWithNotes() {
        return repositoryService.allInstances(DocDemoObjectWithNotes.class);
    }

    public List<DocDemoObjectWithUrl> getExampleDocumentDocDemoObjectWithUrl() {
        return repositoryService.allInstances(DocDemoObjectWithUrl.class);
    }

    public List<DocInvoice> getExampleDocumentDocInvoices() {
        return repositoryService.allInstances(DocInvoice.class);
    }

    public List<DocOtherObject> getExampleDocumentDocOtherObjects() {
        return repositoryService.allInstances(DocOtherObject.class);
    }

    public List<DocumentType> getExampleDocumentDocumentTypes() {
        return documentTypeMenu.allDocumentTypes();
    }

    public List<RenderingStrategy> getExampleDocumentRenderingStrategies() {
        return renderingStrategyRepository.allStrategies();
    }

    public List<DocumentTemplate> getExampleDocumentDocumentTemplates() {
        return documentTemplateRepository.allTemplates();
    }

    public List<org.incode.example.document.dom.impl.applicability.Applicability> getExampleDocumentApplicabilities() {
        return repositoryService.allInstances(org.incode.example.document.dom.impl.applicability.Applicability.class);
    }

    public List<DocumentAbstract> getExampleDocumentDocuments() {
        return documentRepository.allDocuments();
    }

    @javax.inject.Inject
    DocumentTypeMenu documentTypeMenu;

    @javax.inject.Inject
    RenderingStrategyRepository renderingStrategyRepository;

    @javax.inject.Inject
    DocumentTemplateRepository documentTemplateRepository;

    @javax.inject.Inject
    DocumentRepository documentRepository;


    // example.note

    public List<NotableObject> getExampleNoteNotables() {
        return notableObjectRepository.listAll();
    }
    @Inject
    NotableObjectRepository notableObjectRepository;

    public List<NotableLinkForNotableObject> getExampleNoteNotableLinkForNotableObject() {
        return repositoryService.allInstances(NotableLinkForNotableObject.class);
    }

    public List<Note> getExampleNoteNotes() {
        return noteRepository.allNotes();
    }

    @Inject
    NoteRepository noteRepository;




    // example.tags

    public List<TaggableObject> getExampleTagsTaggableObjects() {
        return taggableObjectMenu.listAllTaggableObjects();
    }

    public List<Tag> getExampleTags() {
        return repositoryService.allInstances(Tag.class);
    }

    @Inject
    TaggableObjectMenu taggableObjectMenu;

    @Inject
    RepositoryService repositoryService;





}
