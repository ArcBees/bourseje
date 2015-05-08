/**
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

package com.arcbees.bourseje.client.realtime.numberofvote;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

public class NumberOfVoteView extends ViewImpl implements NumberOfVotePresenter.MyView {
    interface Binder extends UiBinder<Widget, NumberOfVoteView> {
    }

    @UiField
    SpanElement johanieVotes;
    @UiField
    SpanElement dominicVotes;
    @UiField
    SpanElement raphaelVotes;
    @UiField
    SpanElement maximeVotes;
    @UiField
    SpanElement simonVotes;
    @UiField
    SpanElement vincentVotes;

    @Inject
    NumberOfVoteView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setJohanieVotes(int votes) {
        johanieVotes.setInnerText(String.valueOf(votes));
    }

    @Override
    public void setDominicVotes(int votes) {
        dominicVotes.setInnerText(String.valueOf(votes));
    }

    @Override
    public void setRaphaelVotes(int votes) {
        raphaelVotes.setInnerText(String.valueOf(votes));
    }

    @Override
    public void setMaximeVotes(int votes) {
        maximeVotes.setInnerText(String.valueOf(votes));
    }

    @Override
    public void setSimonVotes(int votes) {
        simonVotes.setInnerText(String.valueOf(votes));
    }

    @Override
    public void setVincentVotes(int votes) {
        vincentVotes.setInnerText(String.valueOf(votes));
    }
}
