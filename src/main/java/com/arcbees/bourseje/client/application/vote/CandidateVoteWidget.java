/*
 * Copyright 2016 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.bourseje.client.application.vote;

import com.arcbees.bourseje.shared.Candidate;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class CandidateVoteWidget implements IsWidget {
    interface Binder extends UiBinder<HTMLPanel, CandidateVoteWidget> {
    }

    private final static Binder binder = GWT.create(Binder.class);

    @UiField
    ImageElement image;
    @UiField
    ParagraphElement name;
    @UiField
    ParagraphElement company;
    @UiField
    InputElement inputRadio;
    @UiField(provided = true)
    final String candidateId = Document.get().createUniqueId();

    private final Widget widget;

    public CandidateVoteWidget(Candidate candidate) {
        widget = binder.createAndBindUi(this);

        inputRadio.setId(candidateId);

        company.setInnerText(candidate.getCompany());
        name.setInnerText(candidate.getName());

        if (candidate.getPicture() != null) {
            image.setSrc(candidate.getPicture());
        }
    }

    @Override
    public Widget asWidget() {
        return widget;
    }
}
