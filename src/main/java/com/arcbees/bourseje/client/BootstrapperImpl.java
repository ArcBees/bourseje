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

package com.arcbees.bourseje.client;

import javax.inject.Inject;

import com.arcbees.bourseje.client.api.VoteService;
import com.arcbees.bourseje.client.resources.Resources;
import com.arcbees.bourseje.shared.VoteState;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class BootstrapperImpl implements Bootstrapper {
    private final PlaceManager placeManager;
    private final PlaceHistoryHandler.Historian historian;
    private final RestDispatch restDispatch;
    private final VoteService voteService;

    @Inject
    BootstrapperImpl(
            Resources resources,
            PlaceManager placeManager,
            PlaceHistoryHandler.Historian historian,
            RestDispatch restDispatch,
            VoteService voteService) {
        this.placeManager = placeManager;
        this.historian = historian;
        this.restDispatch = restDispatch;
        this.voteService = voteService;

        resources.styles().ensureInjected();
    }

    @Override
    public void onBootstrap() {
        if (inAdminSection()) {
            placeManager.revealCurrentPlace();
        } else {
            redirectBasedOnCurrentVoteState();
        }
    }

    private boolean inAdminSection() {
        String currentNameToken = historian.getToken();
        return currentNameToken.startsWith(NameTokens.PREFIX_ADMIN);
    }

    private void redirectBasedOnCurrentVoteState() {
        restDispatch.execute(voteService.getCurrentVoteState(), new RestCallbackImpl<VoteState>() {
            @Override
            public void onSuccess(VoteState result) {
                if (result == VoteState.STARTED) {
                    placeManager.revealCurrentPlace();
                } else if (result == VoteState.FINISHED) {
                    revealPlace(NameTokens.VOTE_FINISHED);
                } else {
                    revealPlace(NameTokens.VOTE_INACTIVE);
                }
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
