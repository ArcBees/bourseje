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

package com.arcbees.bourseje.client.admin.dashboard;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arcbees.bourseje.client.AdminRestCallback;
import com.arcbees.bourseje.client.NameTokens;
import com.arcbees.bourseje.client.RestCallbackImpl;
import com.arcbees.bourseje.client.admin.AdminPresenter;
import com.arcbees.bourseje.client.admin.dashboard.candidate.CandidateAdminPresenter;
import com.arcbees.bourseje.client.admin.dashboard.candidate.CandidateAdminPresenterFactory;
import com.arcbees.bourseje.client.api.AdminService;
import com.arcbees.bourseje.client.api.CandidateService;
import com.arcbees.bourseje.client.api.LoginService;
import com.arcbees.bourseje.client.api.VoteService;
import com.arcbees.bourseje.shared.Candidate;
import com.arcbees.bourseje.shared.CandidateResult;
import com.arcbees.bourseje.shared.UrlWrapper;
import com.arcbees.bourseje.shared.VoteState;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class AdminDashboardPresenter extends Presenter<AdminDashboardPresenter.MyView, AdminDashboardPresenter.MyProxy>
        implements AdminDashboardUiHandlers {
    interface MyView extends View, HasUiHandlers<AdminDashboardUiHandlers> {
        void setCurrentState(VoteState currentState);
    }

    @ProxyStandard
    @NameToken(NameTokens.ADMIN_DASHBOARD)
    interface MyProxy extends ProxyPlace<AdminDashboardPresenter> {
    }

    public final static Slot<CandidateAdminPresenter> SLOT_CANDIDATES = new Slot<>();

    private final RestDispatch dispatch;
    private final LoginService loginService;
    private final AdminService adminService;
    private final VoteService voteService;
    private final CandidateService candidateService;
    private final CandidateAdminPresenterFactory presenterWidgetFactory;

    @Inject
    AdminDashboardPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            RestDispatch dispatch,
            LoginService loginService,
            AdminService adminService,
            VoteService voteService,
            CandidateService candidateService,
            CandidateAdminPresenterFactory presenterWidgetFactory) {
        super(eventBus, view, proxy, AdminPresenter.SLOT_MAIN);

        this.dispatch = dispatch;
        this.loginService = loginService;
        this.adminService = adminService;
        this.voteService = voteService;
        this.candidateService = candidateService;
        this.presenterWidgetFactory = presenterWidgetFactory;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onReveal() {
        clearSlot(SLOT_CANDIDATES);

        dispatch.execute(adminService.getVotesPerCandidate(), new AdminRestCallback<Collection<CandidateResult>>() {
            @Override
            public void onSuccess(final Collection<CandidateResult> votesPerCandidate) {
                setVotesPerCandidates(votesPerCandidate);
            }
        });

        dispatch.execute(voteService.getCurrentVoteState(), new AdminRestCallback<VoteState>() {
            @Override
            public void onSuccess(VoteState currentState) {
                getView().setCurrentState(currentState);
            }
        });
    }

    private void setVotesPerCandidates(final Collection<CandidateResult> votesPerCandidate) {
        dispatch.execute(candidateService.getCandidates(), new RestCallbackImpl<List<Candidate>>() {
            @Override
            public void onSuccess(List<Candidate> candidates) {
                createCandidates(candidates, convertToMap(candidates, votesPerCandidate));
            }
        });
    }

    private void createCandidates(List<Candidate> candidates, Map<String, Integer> votesPerCandidates) {
        for (Candidate candidate : candidates) {
            Integer nbOfVotes = votesPerCandidates.get(candidate.getName());
            CandidateAdminPresenter widget = presenterWidgetFactory.create(candidate, nbOfVotes);

            addToSlot(SLOT_CANDIDATES, widget);
        }
    }

    @Override
    public void onLoginClicked() {
        String currentUrl = Window.Location.getHref();

        dispatch.execute(loginService.getLoginUrl(currentUrl), new AdminRestCallback<UrlWrapper>() {
            @Override
            public void onSuccess(UrlWrapper loginUrl) {
                Window.Location.replace(loginUrl.getUrl());
            }
        });
    }

    @Override
    public void onStartVoteClicked() {
        if (Window.confirm("Are you sure? This will reset all the votes.")) {
            setVoteState(VoteState.STARTED);
        }
    }

    @Override
    public void onStopVoteClicked() {
        setVoteState(VoteState.FINISHED);
    }

    @Override
    public void onInactiveVoteClicked() {
        setVoteState(VoteState.INACTIVE);
    }

    private void setVoteState(final VoteState voteState) {
        dispatch.execute(adminService.setVoteState(voteState), new AdminRestCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                getView().setCurrentState(voteState);
            }
        });
    }

    private Map<String, Integer> convertToMap(List<Candidate> candidatesList, Collection<CandidateResult>
            candidateResults) {
        Map<String, Integer> resultsMap = new HashMap<>();
        Map<String, Integer> results = new HashMap<>();

        for (CandidateResult result : candidateResults) {
            resultsMap.put(result.getCandidateName(), result.getNumberOfVotes());
        }

        for (Candidate candidate : candidatesList) {
            if (resultsMap.containsKey(candidate.getName())) {
                results.put(candidate.getName(), resultsMap.get(candidate.getName()));
            } else {
                results.put(candidate.getName(), 0);
            }
        }

        return results;
    }
}
