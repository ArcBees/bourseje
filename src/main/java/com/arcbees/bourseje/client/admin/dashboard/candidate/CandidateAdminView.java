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

package com.arcbees.bourseje.client.admin.dashboard.candidate;

import javax.inject.Inject;

import com.arcbees.bourseje.shared.Candidate;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class CandidateAdminView extends ViewWithUiHandlers<CandidateAdminUiHandlers>
        implements CandidateAdminPresenter.MyView {
    interface Binder extends UiBinder<Widget, CandidateAdminView> {
    }

    @UiField
    HeadingElement name;
    @UiField
    ParagraphElement company;
    @UiField
    ImageElement image;
    @UiField
    SpanElement nbVotes;
    @UiField
    Anchor modify;
    @UiField
    Anchor delete;

    @Inject
    CandidateAdminView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setCandidate(Candidate candidate, int nbOfVotes) {
        name.setInnerHTML(candidate.getName());
        company.setInnerHTML(candidate.getCompany());
        image.setSrc(candidate.getPicture());
        nbVotes.setInnerHTML(String.valueOf(nbOfVotes));
    }

    @UiHandler("modify")
    void onModify(ClickEvent event) {
        getUiHandlers().onModify();
    }

    @UiHandler("delete")
    void onDelete(ClickEvent event) {
        getUiHandlers().onDelete();
    }
}
