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

import com.arcbees.bourseje.client.admin.event.VoteEvent;
import com.arcbees.bourseje.shared.Candidate;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class CandidateVotePresenter extends PresenterWidget<CandidateVotePresenter.MyView>
        implements CandidateVoteUiHandlers {
    interface MyView extends HasUiHandlers<CandidateVoteUiHandlers>, View {
        void setCandidate(Candidate candidate);
    }

    private final Candidate candidate;

    @Inject
    CandidateVotePresenter(
            EventBus eventBus,
            MyView view,
            @Assisted Candidate candidate) {
        super(eventBus, view);
        this.candidate = candidate;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onReveal() {
        getView().setCandidate(candidate);
    }

    @Override
    public void onSelect() {
        VoteEvent.fire(this, candidate);
    }
}
