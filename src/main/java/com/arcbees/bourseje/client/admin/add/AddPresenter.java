/**
 * Copyright 2016 ArcBees Inc.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.bourseje.client.admin.add;

import com.arcbees.bourseje.client.NameTokens;
import com.arcbees.bourseje.client.admin.AdminPresenter;
import com.arcbees.bourseje.client.api.AdminService;
import com.google.gwt.query.client.GQuery;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class AddPresenter extends Presenter<AddPresenter.MyView, AddPresenter.MyProxy>
        implements AddUiHandlers {
    interface MyView extends View, HasUiHandlers<AddUiHandlers> {
    }

    @ProxyStandard
    @NameToken(NameTokens.ADD)
    interface MyProxy extends ProxyPlace<AddPresenter> {
    }

    private final RestDispatch dispatch;
    private final AdminService adminService;

    @Inject
    AddPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            RestDispatch dispatch,
            AdminService adminService) {
        super(eventBus, view, proxy, AdminPresenter.SLOT_MAIN);
        this.dispatch = dispatch;
        this.adminService = adminService;

        getView().setUiHandlers(this);
    }

    @Override
    public void onAddCandidateClicked() {
        GQuery.console.log("Bouh");
    }
}
