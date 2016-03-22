/**
 * Copyright 2016 ArcBees Inc.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.bourseje.client.admin.add;

import javax.inject.Inject;

import com.arcbees.bourseje.shared.Candidate;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.google.gwt.dom.client.BrowserEvents.CLICK;
import static com.google.gwt.query.client.GQuery.$;

public class AddView extends ViewWithUiHandlers<AddUiHandlers> implements AddPresenter.MyView {
    interface Binder extends UiBinder<Widget, AddView> {
    }

    @UiField
    ButtonElement addCandidate;
    @UiField
    InputElement name;
    @UiField
    InputElement company;

    @Inject
    AddView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        initButtons();
    }

    private void initButtons() {
        $(addCandidate).on(CLICK, new Function() {
            @Override
            public void f() {
                if(!name.getValue().isEmpty() && !company.getValue().isEmpty()) {
                    Candidate candidate = new Candidate(name.getValue(), company.getValue(), null);

                    getUiHandlers().onAddCandidateClicked(candidate);
                }
            }
        });
    }
}
