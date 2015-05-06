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

package com.arcbees.bourseje.client.application.confirmvote;

import com.arcbees.bourseje.client.NameTokens;
import com.arcbees.bourseje.client.RestCallbackImpl;
import com.arcbees.bourseje.client.api.VoteService;
import com.arcbees.bourseje.client.application.ApplicationPresenter;
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
    }

    @ProxyStandard
    @NameToken(NameTokens.CONFIRM_VOTE)
    interface MyProxy extends ProxyPlace<ConfirmVotePresenter> {
    }

    private final PlaceManager placeManager;
    private final RestDispatch dispatcher;
    private final VoteService voteService;

    private String name;
    private String company;

    @Inject
    ConfirmVotePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager,
            RestDispatch dispatcher,
            VoteService voteService) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        this.voteService = voteService;

        getView().setUiHandlers(this);
    }


    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);

        name = request.getParameter(NameTokens.NAME, "noSelection");
        company = request.getParameter(NameTokens.COMPANY, "noSelection");

        getView().setName(name);
        getView().setCompany(company);
    }

    @Override
    public void onConfirmClicked() {
        VoteItem voteItem = new VoteItem(name + " " + company);
        dispatcher.execute(voteService.vote(voteItem), new RestCallbackImpl<Void>() {
            @Override
            public void onError(Response response) {
                super.onError(response);

                PlaceRequest placeRequest = new PlaceRequest.Builder()
                        .nameToken(NameTokens.ALREADY_VOTED)
                        .build();

                placeManager.revealPlace(placeRequest);
            }

            @Override
            public void onSuccess(Void aVoid) {
                PlaceRequest placeRequest = new PlaceRequest.Builder()
                        .nameToken(NameTokens.THANKS)
                        .build();

                placeManager.revealPlace(placeRequest);

                Cookies.setCookie("voted", "");
            }
        });
    }
}
