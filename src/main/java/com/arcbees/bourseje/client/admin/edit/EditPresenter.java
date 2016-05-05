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

package com.arcbees.bourseje.client.admin.edit;

import com.arcbees.bourseje.client.AdminRestCallback;
import com.arcbees.bourseje.client.NameTokens;
import com.arcbees.bourseje.client.RestCallbackImpl;
import com.arcbees.bourseje.client.admin.AdminPresenter;
import com.arcbees.bourseje.client.api.AdminService;
import com.arcbees.bourseje.client.api.CandidateService;
import com.arcbees.bourseje.shared.Candidate;
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

public class EditPresenter extends Presenter<EditPresenter.MyView, EditPresenter.MyProxy>
        implements EditUiHandlers {

    private Candidate candidateToUpdate;

    interface MyView extends View, HasUiHandlers<EditUiHandlers> {
        void setCandidate(Candidate candidate);
    }

    @ProxyStandard
    @NameToken(NameTokens.EDIT)
    interface MyProxy extends ProxyPlace<EditPresenter> {
    }

    private final RestDispatch dispatch;
    private final AdminService adminService;
    private CandidateService candidateService;
    private final PlaceManager placeManager;

    @Inject
    EditPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            RestDispatch dispatch,
            AdminService adminService,
            CandidateService candidateService,
            PlaceManager placeManager) {
        super(eventBus, view, proxy, AdminPresenter.SLOT_MAIN);

        this.dispatch = dispatch;
        this.adminService = adminService;
        this.candidateService = candidateService;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        Long candidateId = Long.valueOf(request.getParameter(NameTokens.PARAM_ID, "invalid parameter"));

        dispatch.execute(candidateService.getCandidateById(candidateId), new RestCallbackImpl<Candidate>() {
            @Override
            public void onSuccess(Candidate candidate) {
                candidateToUpdate = candidate;
                getView().setCandidate(candidate);
            }
        });
    }

    @Override
    public void onAddCandidateClicked(Candidate candidate) {
        dispatch.execute(adminService.updateCandidate(candidateToUpdate.getId(), candidate), new AdminRestCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                goToDashboard();
            }
        });
    }

    @Override
    public void onCancel() {
        goToDashboard();
    }

    private void goToDashboard() {
        PlaceRequest placeRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.ADMIN_DASHBOARD)
                .build();

        placeManager.revealPlace(placeRequest);
    }
}
