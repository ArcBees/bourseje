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

package com.arcbees.bourseje.client.admin.dashboard;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.arcbees.bourseje.client.model.Candidates;
import com.arcbees.bourseje.shared.CandidateResult;
import com.arcbees.bourseje.shared.VoteState;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import static com.arcbees.gaestudio.repackaged.com.google.gwt.dom.client.BrowserEvents.CLICK;
import static com.google.gwt.query.client.GQuery.$;

public class AdminDashboardView extends ViewWithUiHandlers<AdminDashboardUiHandlers>
        implements AdminDashboardPresenter.MyView {
    interface Binder extends UiBinder<Widget, AdminDashboardView> {
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
    @UiField
    ButtonElement login;
    @UiField
    ButtonElement inactiveVote;
    @UiField
    ButtonElement startVote;
    @UiField
    ButtonElement stopVote;
    @UiField
    Element currentState;

    private Map<String, SpanElement> numberOfVoteElements = new HashMap<>();

    @Inject
    AdminDashboardView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        numberOfVoteElements.put(Candidates.JOHANIE.getName(), johanieVotes);
        numberOfVoteElements.put(Candidates.DOMINIC.getName(), dominicVotes);
        numberOfVoteElements.put(Candidates.RAPHAEL.getName(), raphaelVotes);
        numberOfVoteElements.put(Candidates.MAXIME.getName(), maximeVotes);
        numberOfVoteElements.put(Candidates.SIMON.getName(), simonVotes);
        numberOfVoteElements.put(Candidates.VINCENT.getName(), vincentVotes);

        initButtons();
    }

    private void initButtons() {
        $(login).on(CLICK, new Function() {
            @Override
            public void f() {
                getUiHandlers().onLoginClicked();
            }
        });

        $(inactiveVote).on(CLICK, new Function() {
            @Override
            public void f() {
                getUiHandlers().onInactiveVoteClicked();
            }
        });

        $(startVote).on(CLICK, new Function() {
            @Override
            public void f() {
                getUiHandlers().onStartVoteClicked();
            }
        });

        $(stopVote).on(CLICK, new Function() {
            @Override
            public void f() {
                getUiHandlers().onStopVoteClicked();
            }
        });

    }

    @Override
    public void setNumberOfVotesForCandidate(CandidateResult candidateResult) {
        SpanElement element = numberOfVoteElements.get(candidateResult.getCandidateName());

        if (element == null) {
            GQuery.console.error("Candidate not found: " + candidateResult.getCandidateName());
        } else {
            element.setInnerText(String.valueOf(candidateResult.getNumberOfVotes()));
        }
    }

    @Override
    public void setCurrentState(VoteState currentState) {
        this.currentState.setInnerText(currentState.toString());
    }
}
