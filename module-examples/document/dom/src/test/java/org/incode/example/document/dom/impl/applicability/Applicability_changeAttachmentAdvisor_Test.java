package org.incode.example.document.dom.impl.applicability;

import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2;

import org.incode.example.document.dom.impl.docs.DocumentTemplate;
import org.incode.example.document.dom.services.ClassService;
import org.incode.example.document.dom.spi.AttachmentAdvisorClassNameService;

public class Applicability_changeAttachmentAdvisor_Test {

    @Rule
    public JUnitRuleMockery2 context = JUnitRuleMockery2.createFor(JUnitRuleMockery2.Mode.INTERFACES_AND_CLASSES);

    @Mock
    private AttachmentAdvisorClassNameService mockClassNameService;

    @Mock
    private ClassService mockClassService;

    @Mock
    private DocumentTemplate mockDocumentTemplate;

    @Mock
    Applicability mockApplicability;

    Applicability_changeAttachmentAdvisor mixin;

    @Before
    public void setUp() throws Exception {
        // when
        mixin = new Applicability_changeAttachmentAdvisor(mockApplicability);
        mixin.classNameService = mockClassNameService;
        mixin.classService = mockClassService;
    }

    public static class DisabledTest extends Applicability_changeAttachmentAdvisor_Test {

        @Ignore
        @Test
        public void disabled_if_no_AttachmentAdvisorClassNameService_available() throws Exception {

        }

        @Ignore
        @Test
        public void enabled_if_AttachmentAdvisorClassNameService_is_available() throws Exception {

        }
    }

    public static class Choices_Test extends Applicability_changeAttachmentAdvisor_Test {

        @Ignore
        @Test
        public void delegates_off_to_AttachmentAdvisorClassNameService() throws Exception {

        }
    }

    public static class Default_Test extends Applicability_changeAttachmentAdvisor_Test {

        @Ignore
        @Test
        public void creates_view_model_from_ClassService() throws Exception {

        }
    }

    public static class ActionInvocation_Test extends Applicability_changeAttachmentAdvisor_Test {

        @Ignore
        @Test
        public void happy_case() throws Exception {

        }
    }


}