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

package com.arcbees.bourseje.client.application.home;

import java.util.List;

import com.arcbees.bourseje.client.NameTokens;
import com.arcbees.bourseje.client.RestCallbackImpl;
import com.arcbees.bourseje.client.api.VoteService;
import com.arcbees.bourseje.client.application.ApplicationPresenter;
import com.arcbees.bourseje.shared.VoteItem;
import com.google.gwt.http.client.Response;
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
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> implements HomeUiHandlers {
    interface MyView extends View, HasUiHandlers<HomeUiHandlers> {
    }

    @ProxyStandard
    @NameToken(NameTokens.HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }

    private final PlaceManager placeManager;
    private final RestDispatch dispatcher;
    private final VoteService voteService;

    @Inject
    HomePresenter(
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
    public void onCliquezClicked() {
        dispatcher.execute(voteService.getVoteItems(), new RestCallbackImpl<List<VoteItem>>() {
            @Override
            public void onSuccess(List<VoteItem> voteItems) {
                PlaceRequest request = new Builder()
                        .nameToken(NameTokens.VOTE)
                        .build();

                placeManager.revealPlace(request);
            }

            @Override
            public void onError(Response response) {
                int statusCode = response.getStatusCode();
                if (statusCode == Response.SC_FORBIDDEN || statusCode == Response.SC_SERVICE_UNAVAILABLE) {
                    handleRedirection(statusCode);
                } else {
                    super.onError(response);
                }
            }
        });
    }

    private void handleRedirection(int statusCode) {
        Builder requestBuilder = new Builder();
        if (statusCode == Response.SC_FORBIDDEN) {
            requestBuilder.nameToken(NameTokens.NO_VOTE);

            placeManager.revealPlace(requestBuilder.build());
        } else if (statusCode == Response.SC_SERVICE_UNAVAILABLE) {
            requestBuilder.nameToken(NameTokens.NO_VOTE);

            placeManager.revealPlace(requestBuilder.build());
        }
    }
}
