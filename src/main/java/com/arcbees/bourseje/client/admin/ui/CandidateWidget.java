/**
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

package com.arcbees.bourseje.client.admin.ui;

import com.arcbees.bourseje.shared.Candidate;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class CandidateWidget implements IsWidget {
    interface Binder extends UiBinder<HTMLPanel, CandidateWidget> {
    }

    private final static Binder binder = GWT.create(Binder.class);

    @UiField
    ParagraphElement company;
    @UiField
    HeadingElement name;
    @UiField
    Image image;
    @UiField
    SpanElement nbVotes;

    private final Widget widget;

    public CandidateWidget(Candidate candidate, int nbOfVotes) {
        widget = binder.createAndBindUi(this);

        company.setInnerText(candidate.getCompany());
        name.setInnerText(candidate.getName());

        if (candidate.getPicture() != null) {
            image.setResource(candidate.getPicture());
        }

        nbVotes.setInnerText(String.valueOf(nbOfVotes));
    }

    @Override
    public Widget asWidget() {
        return widget;
    }
}
