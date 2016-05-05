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

package com.arcbees.bourseje.client.admin.winner;

import com.arcbees.bourseje.client.AdminRestCallback;
import com.arcbees.bourseje.client.NameTokens;
import com.arcbees.bourseje.client.admin.AdminPresenter;
import com.arcbees.bourseje.client.api.AdminService;
import com.arcbees.bourseje.client.api.CandidateService;
import com.arcbees.bourseje.shared.Candidate;
import com.arcbees.bourseje.shared.CandidateResult;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class WinnerPresenter extends Presenter<WinnerPresenter.MyView, WinnerPresenter.MyProxy> {
    interface MyView extends View {
        void setPicture(String picture);

        void setName(String name);

        void setCompany(String company);

        void setVotes(int numberOfVotes);
    }

    @ProxyStandard
    @NameToken(NameTokens.WINNER)
    interface MyProxy extends ProxyPlace<WinnerPresenter> {
    }

    private final RestDispatch dispatch;
    private final AdminService adminService;
    private final CandidateService candidateService;

    @Inject
    WinnerPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            RestDispatch dispatch,
            AdminService adminService,
            CandidateService candidateService) {
        super(eventBus, view, proxy, AdminPresenter.SLOT_MAIN);

        this.dispatch = dispatch;
        this.adminService = adminService;
        this.candidateService = candidateService;
    }

    @Override
    protected void onReveal() {
        dispatch.execute(adminService.getWinner(), new AdminRestCallback<CandidateResult>() {
            @Override
            public void onSuccess(final CandidateResult candidateResult) {
                setCandidateResultsInView(candidateResult);
            }
        });
    }

    private void setCandidateResultsInView(final CandidateResult candidateResult) {
        dispatch.execute(candidateService.getCandidateById(candidateResult.getCandidateId()),
                new AdminRestCallback<Candidate>() {
                    @Override
                    public void onSuccess(Candidate candidate) {
                        setInView(candidate, candidateResult.getNumberOfVotes());
                    }
                });
    }

    private void setInView(Candidate candidate, int votes) {
        getView().setPicture(candidate.getPicture());
        getView().setName(candidate.getName());
        getView().setCompany(candidate.getCompany());
        getView().setVotes(votes);
    }
}
