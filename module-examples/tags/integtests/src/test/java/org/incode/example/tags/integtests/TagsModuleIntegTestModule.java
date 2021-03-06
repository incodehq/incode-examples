package org.incode.example.tags.integtests;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Sets;

import org.apache.isis.applib.Module;
import org.apache.isis.applib.ModuleAbstract;

import org.isisaddons.module.fakedata.FakeDataModule;

import org.incode.example.tags.demo.shared.TagsDemoSharedModule;

@XmlRootElement(name = "module")
public class TagsModuleIntegTestModule extends ModuleAbstract {
    @Override
    public Set<Module> getDependencies() {
        return Sets.newHashSet(
                new TagsDemoSharedModule(),
                new FakeDataModule()
        );
    }
}
