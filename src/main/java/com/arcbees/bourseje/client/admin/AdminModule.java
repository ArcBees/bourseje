/**
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

package com.arcbees.bourseje.client.admin;

import com.arcbees.bourseje.client.admin.add.AddModule;
import com.arcbees.bourseje.client.admin.dashboard.AdminDashboardModule;
import com.arcbees.bourseje.client.admin.edit.EditModule;
import com.arcbees.bourseje.client.admin.dashboard.candidate.CandidateAdminModule;
import com.arcbees.bourseje.client.admin.winner.WinnerModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AdminModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new AdminDashboardModule());
        install(new WinnerModule());
        install(new AddModule());
        install(new EditModule());
        install(new CandidateAdminModule());

        bindPresenter(AdminPresenter.class, AdminPresenter.MyView.class, AdminView.class,
                AdminPresenter.MyProxy.class);
    }
}
