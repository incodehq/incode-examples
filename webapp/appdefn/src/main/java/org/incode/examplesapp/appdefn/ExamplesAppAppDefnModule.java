package org.incode.examplesapp.appdefn;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Sets;

import org.apache.isis.applib.Module;
import org.apache.isis.applib.ModuleAbstract;

import org.isisaddons.module.security.dom.password.PasswordEncryptionServiceUsingJBcrypt;
import org.isisaddons.module.security.dom.permission.PermissionsEvaluationServiceAllowBeatsVeto;

import org.incode.example.alias.demo.AliasDemoModule;
import org.incode.example.classification.demo.ClassificationDemoModule;
import org.incode.example.commchannel.demo.CommChannelDemoModule;
import org.incode.example.communications.demo.CommunicationsDemoModule;
import org.incode.example.country.CountryModule;
import org.incode.example.docfragment.demo.DocFragmentDemoModule;
import org.incode.example.docrendering.freemarker.FreemarkerDocRenderingModule;
import org.incode.example.docrendering.stringinterpolator.StringInterpolatorDocRenderingModule;
import org.incode.example.docrendering.xdocreport.XDocReportDocRenderingModule;
import org.incode.example.document.demo.DocumentDemoModule;
import org.incode.example.tags.demo.TagsDemoModule;
import org.incode.examples.note.demo.NoteDemoModule;

/**
 * at runtime, also add:
 *
 * -Dorg.incode.example.commchannel.dom.api.GeocodingService.apiKey=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 *
 */
@XmlRootElement(name = "module")
public class ExamplesAppAppDefnModule extends ModuleAbstract {

    public ExamplesAppAppDefnModule() {
        withConfigurationPropertiesFile(getClass(),
                "isis.properties",
                "authentication_shiro.properties",
                "persistor_datanucleus.properties",
                "viewer_restfulobjects.properties",
                "viewer_wicket.properties"
        );
        withConfigurationProperty("isis.viewer.wicket.rememberMe.cookieKey", "ExtendedAppEncryptionKey");
    }

    @Override
    public Set<Class<?>> getAdditionalServices() {
        // necessary because of ISIS-1710
        return Sets.newHashSet(
                PasswordEncryptionServiceUsingJBcrypt.class,
                PermissionsEvaluationServiceAllowBeatsVeto.class);
    }

    @Override
    public Set<Module> getDependencies() {
        final HashSet<Module> dependencies = Sets.newHashSet();

        appendExampleFixtureModulesTo(dependencies);
        appendWktModulesTo(dependencies);

        return dependencies;
    }

    private void appendExampleFixtureModulesTo(Set<Module> dependencies) {
        final HashSet<ModuleAbstract> mods = Sets.newHashSet(
                new AliasDemoModule(),
                new ClassificationDemoModule(),
                new CommChannelDemoModule(),
                new CommunicationsDemoModule(),
                new CountryModule(),
                new DocFragmentDemoModule(),
                new FreemarkerDocRenderingModule(),
                new StringInterpolatorDocRenderingModule(),
                new XDocReportDocRenderingModule(),
                new DocumentDemoModule(),
                new NoteDemoModule(),
                new TagsDemoModule()
        );
        dependencies.addAll(mods);
    }

    private void appendWktModulesTo(Set<Module> dependencies) {
//        final HashSet<ModuleAbstract> mods = Sets.newHashSet(
//                new PdfjsCptModule()
//        );
//        dependencies.addAll(mods);
    }


}
