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

import java.util.Collection;

import com.arcbees.bourseje.client.AdminRestCallback;
import com.arcbees.bourseje.client.NameTokens;
import com.arcbees.bourseje.client.admin.AdminPresenter;
import com.arcbees.bourseje.client.api.AdminService;
import com.arcbees.bourseje.client.api.LoginService;
import com.arcbees.bourseje.shared.CandidateResult;
import com.arcbees.bourseje.shared.UrlWrapper;
import com.arcbees.bourseje.shared.VoteState;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class AdminDashboardPresenter extends Presenter<AdminDashboardPresenter.MyView, AdminDashboardPresenter.MyProxy>
        implements AdminDashboardUiHandlers {
    interface MyView extends View, HasUiHandlers<AdminDashboardUiHandlers> {
        void setNumberOfVotesForCandidate(CandidateResult candidateResult);
    }

    @ProxyStandard
    @NameToken(NameTokens.ADMIN_DASHBOARD)
    interface MyProxy extends ProxyPlace<AdminDashboardPresenter> {
    }

    private final RestDispatch dispatch;
    private final LoginService loginService;
    private final AdminService adminService;

    @Inject
    AdminDashboardPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            RestDispatch dispatch,
            LoginService loginService,
            AdminService adminService) {
        super(eventBus, view, proxy, AdminPresenter.SLOT_MAIN);

        this.dispatch = dispatch;
        this.loginService = loginService;
        this.adminService = adminService;

        getView().setUiHandlers(this);
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
        if (!Window.confirm("Are you sure? This will reset all the votes.")) {
            return;
        }

        dispatch.execute(adminService.setVoteState(VoteState.STARTED), new AdminRestCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                GQuery.console.info("Vote started!");
            }
        });
    }

    @Override
    protected void onReveal() {
        dispatch.execute(adminService.getVotesPerCandidate(), new AdminRestCallback<Collection<CandidateResult>>() {
            @Override
            public void onSuccess(Collection<CandidateResult> result) {
                for (CandidateResult candidateResult : result) {
                    getView().setNumberOfVotesForCandidate(candidateResult);
                }
            }
        });
    }
}
