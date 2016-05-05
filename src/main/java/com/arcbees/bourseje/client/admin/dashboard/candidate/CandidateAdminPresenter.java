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

package com.arcbees.bourseje.client.admin.dashboard.candidate;

import javax.inject.Inject;

import com.arcbees.bourseje.client.AdminRestCallback;
import com.arcbees.bourseje.client.NameTokens;
import com.arcbees.bourseje.client.api.AdminService;
import com.arcbees.bourseje.shared.Candidate;
import com.google.gwt.user.client.Window;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class CandidateAdminPresenter extends PresenterWidget<CandidateAdminPresenter.MyView>
        implements CandidateAdminUiHandlers {
    interface MyView extends View, HasUiHandlers<CandidateAdminUiHandlers> {
        void setCandidate(Candidate candidate, int nbOfVotes);
    }

    private final Candidate candidate;
    private final PlaceManager placeManager;
    private final AdminService adminService;
    private final RestDispatch dispatch;
    private final int nbOfVotes;

    @Inject
    CandidateAdminPresenter(
            EventBus eventBus,
            MyView view,
            PlaceManager placeManager,
            AdminService adminService,
            RestDispatch dispatch,
            @Assisted Candidate candidate,
            @Assisted int nbOfVotes) {
        super(eventBus, view);

        this.placeManager = placeManager;
        this.adminService = adminService;
        this.dispatch = dispatch;
        this.candidate = candidate;
        this.nbOfVotes = nbOfVotes;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        getView().setCandidate(candidate, nbOfVotes);
    }

    @Override
    public void onModify() {
        PlaceRequest placeRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.EDIT)
                .with(NameTokens.PARAM_ID, String.valueOf(candidate.getId()))
                .build();

        placeManager.revealPlace(placeRequest);
    }

    @Override
    public void onDelete() {
        if (Window.confirm("Are you sure you want to delete " + candidate.getName() + " ?")) {
            dispatch.execute(adminService.removeCandidate(candidate.getId()), new AdminRestCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    removeFromParentSlot();
                }
            });
        }
    }
}
