/*
 *  Copyright 2016 incode.org
 *
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
package org.incode.module.document.dom.impl.docs;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.Mixin;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;

import org.incode.module.document.dom.DocumentModule;

@Mixin
public class DocumentTemplate_updateText {

    //region > constructor
    private final DocumentTemplate documentTemplate;

    public DocumentTemplate_updateText(final DocumentTemplate documentTemplate) {
        this.documentTemplate = documentTemplate;
    }
    //endregion


    public static class ActionDomainEvent extends DocumentModule.ActionDomainEvent<DocumentTemplate_updateText>  { }
    @Action(
            semantics = SemanticsOf.IDEMPOTENT,
            domainEvent = ActionDomainEvent.class
    )
    @ActionLayout(contributed = Contributed.AS_ACTION)
    public DocumentTemplate $$(
            @ParameterLayout(named = "Text", multiLine = DocumentModule.Constants.TEXT_MULTILINE)
            final String text
    ) {
        documentTemplate.setText(text);
        return documentTemplate;
    }

    public String default0$$() {
        return documentTemplate.getText();
    }

    public boolean hide$$() {
        return documentTemplate.getSort() != DocumentSort.TEXT;
    }


}
