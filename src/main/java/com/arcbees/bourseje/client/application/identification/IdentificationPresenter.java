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

package com.arcbees.bourseje.client.application.identification;

import com.arcbees.bourseje.client.NameTokens;
import com.arcbees.bourseje.client.RestCallbackImpl;
import com.arcbees.bourseje.client.api.VoteService;
import com.arcbees.bourseje.client.application.ApplicationPresenter;
import com.arcbees.bourseje.shared.CookieNames;
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

import static com.google.gwt.http.client.Response.SC_FORBIDDEN;

public class IdentificationPresenter
        extends Presenter<IdentificationPresenter.MyView, IdentificationPresenter.MyProxy>
        implements IdentificationUiHandlers {
    interface MyView extends View, HasUiHandlers<IdentificationUiHandlers> {
        void hideInvalidCodeError();

        void showInvalidCodeError();
    }

    @ProxyStandard
    @NameToken(NameTokens.IDENTIFICATION)
    interface MyProxy extends ProxyPlace<IdentificationPresenter> {
    }

    private final PlaceManager placeManager;
    private final RestDispatch dispatcher;
    private final VoteService voteService;

    @Inject
    IdentificationPresenter(
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
    protected void onReveal() {
        getView().hideInvalidCodeError();
    }

    @Override
    public void onSubmit(final String code) {
        dispatcher.execute(voteService.useCode(code), new RestCallbackImpl<Void>() {
            @Override
            public void onError(Response response) {
                super.onError(response);

                if (response.getStatusCode() == SC_FORBIDDEN) {
                    revealPlace(NameTokens.ALREADY_VOTED);
                } else {
                    getView().showInvalidCodeError();
                }
            }

            @Override
            public void onSuccess(Void result) {
                Cookies.setCookie(CookieNames.VOTE_CODE, code);

                revealPlace(NameTokens.VOTE);
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
