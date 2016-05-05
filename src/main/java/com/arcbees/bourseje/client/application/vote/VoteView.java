/*
 * Copyright 2014 ArcBees Inc.
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

import javax.inject.Inject;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class VoteView extends ViewWithUiHandlers<VoteUiHandlers> implements VotePresenter.MyView {
    interface Binder extends UiBinder<Widget, VoteView> {
    }

    @UiField
    DivElement voteGroup;
    @UiField
    HTMLPanel candidatesElement;
    @UiField
    Button submit;

    @Inject
    VoteView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(VotePresenter.SLOT_CANDIDATES, candidatesElement);
    }

    @UiHandler("submit")
    void onSubmit(ClickEvent event) {
        getUiHandlers().onSubmit();
    }
}
