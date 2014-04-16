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

package com.arcbees.bourseje.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);

        bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);

        bindPresenter(NoVotePresenter.class, NoVotePresenter.MyView.class, NoVoteView.class,
                NoVotePresenter.MyProxy.class);

        bindPresenter(InactiveVotePresenter.class, InactiveVotePresenter.MyView.class, InactiveVoteView.class,
                InactiveVotePresenter.MyProxy.class);

        bindPresenter(VotePresenter.class, VotePresenter.MyView.class, VoteView.class, VotePresenter.MyProxy.class);

        bindPresenter(ConfirmVotePresenter.class, ConfirmVotePresenter.MyView.class, ConfirmVoteView.class,
                ConfirmVotePresenter.MyProxy.class);

        bindPresenter(ThanksPresenter.class, ThanksPresenter.MyView.class, ThanksView.class,
                ThanksPresenter.MyProxy.class);

        bindPresenter(AlreadyVotedPresenter.class, AlreadyVotedPresenter.MyView.class, AlreadyVotedView.class,
                AlreadyVotedPresenter.MyProxy.class);
    }
}
