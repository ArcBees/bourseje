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

import java.util.Collection;

import com.arcbees.bourseje.client.NameTokens;
import com.arcbees.bourseje.client.RestCallbackImpl;
import com.arcbees.bourseje.client.api.AdminService;
import com.arcbees.bourseje.client.api.VoteService;
import com.arcbees.bourseje.client.realtime.RealtimePresenter;
import com.arcbees.bourseje.shared.CandidateResult;
import com.arcbees.bourseje.shared.VoteState;
import com.google.gwt.query.client.GQuery;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class NumberOfVotePresenter extends Presenter<NumberOfVotePresenter.MyView, NumberOfVotePresenter.MyProxy>
        implements NumberOfVoteUiHandlers {
    interface MyView extends View, HasUiHandlers<NumberOfVoteUiHandlers> {
        void setNumberOfVotesForCandidate(CandidateResult candidateResult);
    }

    @ProxyStandard
    @NameToken(NameTokens.NUMBER_OF_VOTE)
    interface MyProxy extends ProxyPlace<NumberOfVotePresenter> {
    }

    private final RestDispatch dispatch;
    private final VoteService voteService;
    private final AdminService adminService;

    @Inject
    NumberOfVotePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            RestDispatch dispatch,
            VoteService voteService,
            AdminService adminService) {
        super(eventBus, view, proxy, RealtimePresenter.SLOT_MAIN);

        this.dispatch = dispatch;
        this.voteService = voteService;
        this.adminService = adminService;

        getView().setUiHandlers(this);
    }

    @Override
    public void onStartVoteClicked() {
        dispatch.execute(adminService.setVoteState(VoteState.STARTED), new RestCallbackImpl<Void>() {
            @Override
            public void onSuccess(Void result) {
                GQuery.console.info("Vote started!");
            }
        });
    }

    @Override
    protected void onReveal() {
        dispatch.execute(voteService.getVotesPerCandidate(), new RestCallbackImpl<Collection<CandidateResult>>() {
            @Override
            public void onSuccess(Collection<CandidateResult> result) {
                for (CandidateResult candidateResult : result) {
                    getView().setNumberOfVotesForCandidate(candidateResult);
                }
            }
        });
    }
}
