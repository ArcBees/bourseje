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

package com.arcbees.bourseje.client.application.confirmvote;

import com.arcbees.bourseje.client.NameTokens;
import com.arcbees.bourseje.client.RestCallbackImpl;
import com.arcbees.bourseje.client.api.CandidateService;
import com.arcbees.bourseje.client.api.VoteService;
import com.arcbees.bourseje.client.application.ApplicationPresenter;
import com.arcbees.bourseje.shared.Candidate;
import com.arcbees.bourseje.shared.CookieNames;
import com.arcbees.bourseje.shared.VoteItem;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Cookies;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class ConfirmVotePresenter extends Presenter<ConfirmVotePresenter.MyView, ConfirmVotePresenter.MyProxy>
        implements ConfirmVoteUiHandlers {
    interface MyView extends View, HasUiHandlers<ConfirmVoteUiHandlers> {
        void setName(String name);

        void setCompany(String company);

        void setPictureSource(String source);
    }

    @ProxyStandard
    @NameToken(NameTokens.CONFIRM_VOTE)
    interface MyProxy extends ProxyPlace<ConfirmVotePresenter> {
    }

    private final PlaceManager placeManager;
    private final RestDispatch dispatch;
    private final VoteService voteService;
    private final CandidateService candidateService;

    private String id;

    @Inject
    ConfirmVotePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager,
            RestDispatch dispatch,
            VoteService voteService,
            CandidateService candidateService) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.placeManager = placeManager;
        this.dispatch = dispatch;
        this.voteService = voteService;
        this.candidateService = candidateService;

        getView().setUiHandlers(this);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        if (Cookies.getCookie(CookieNames.VOTE_CODE) == null) {
            revealPlace(NameTokens.IDENTIFICATION);
            return;
        }

        id = request.getParameter(NameTokens.PARAM_ID, "noSelection");
        setCandidateInView(id);
    }

    private void setCandidateInView(String id) {
        dispatch.execute(candidateService.getCandidateById(Long.valueOf(id)), new RestCallbackImpl<Candidate>() {
            @Override
            public void onFailure(Throwable throwable) {
                revealPlace(NameTokens.VOTE);
            }

            @Override
            public void onSuccess(Candidate candidate) {
                getView().setName(candidate.getName());
                getView().setCompany(candidate.getCompany());
                getView().setPictureSource(candidate.getPicture());
            }
        });
    }

    @Override
    public void onConfirmClicked() {
        VoteItem voteItem = new VoteItem(Long.valueOf(id));
        dispatch.execute(voteService.vote(voteItem), new RestCallbackImpl<Void>() {
            @Override
            public void onError(Response response) {
                super.onError(response);

                if (response.getStatusCode() == Response.SC_FORBIDDEN) {
                    revealPlace(NameTokens.ALREADY_VOTED);
                } else {
                    revealPlace(NameTokens.VOTE_FINISHED);
                }
            }

            @Override
            public void onSuccess(Void aVoid) {
                revealPlace(NameTokens.THANKS);
            }
        });
    }

    private void revealPlace(String nameToken) {
        PlaceRequest placeRequest = new PlaceRequest.Builder()
                .nameToken(nameToken)
                .build();

        placeManager.revealPlace(placeRequest);
    }
}
