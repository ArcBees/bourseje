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

package com.arcbees.bourseje.client.application.vote.candidate;

import javax.inject.Inject;

import com.arcbees.bourseje.shared.Candidate;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.query.client.GQuery.$;

public class CandidateVoteView extends ViewWithUiHandlers<CandidateVoteUiHandlers>
        implements CandidateVotePresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, CandidateVoteView> {
    }

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

    @Inject
    CandidateVoteView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        inputRadio.setId(candidateId);

        bind();
    }

    private void bind() {
        $(inputRadio).change(new Function() {
            @Override
            public void f() {
                getUiHandlers().onSelect();
            }
        });
    }

    @Override
    public void setCandidate(Candidate candidate) {
        image.setSrc(candidate.getPicture());
        name.setInnerText(candidate.getName());
        company.setInnerText(candidate.getCompany());
    }
}
