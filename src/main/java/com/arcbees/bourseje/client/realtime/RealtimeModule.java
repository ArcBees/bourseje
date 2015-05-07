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

package com.arcbees.bourseje.client.realtime;

import com.arcbees.bourseje.client.realtime.candidates.CandidatesModule;
import com.arcbees.bourseje.client.realtime.endofvote.EndOfVoteModule;
import com.arcbees.bourseje.client.realtime.howtovote.HowToVoteModule;
import com.arcbees.bourseje.client.realtime.numberofvote.NumberOfVoteModule;
import com.arcbees.bourseje.client.realtime.result.ResultModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class RealtimeModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HowToVoteModule());
        install(new CandidatesModule());
        install(new NumberOfVoteModule());
        install(new ResultModule());
        install(new EndOfVoteModule());

        bindPresenter(RealtimePresenter.class, RealtimePresenter.MyView.class, RealtimeView.class,
                RealtimePresenter.MyProxy.class);
    }
}
