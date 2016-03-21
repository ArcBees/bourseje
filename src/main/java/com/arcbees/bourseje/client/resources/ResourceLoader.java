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

package com.arcbees.bourseje.client.resources;

import javax.inject.Inject;

public class ResourceLoader {
    @Inject
    ResourceLoader(
            Resources appResources,
            AnimationsResources animationsResources,
            PageAlreadyVotedResources pageAlreadyVotedResources,
            PageConfirmVoteResources pageConfirmVoteResources,
            PageNoVoteResources pageNoVoteResources,
            PageHomeResources pageHomeResources,
            PageThanksResources pageThanksResources,
            PageVoteResources pageVoteResources,
            PageIdentificationResources pageIdentificationResources,
            PageVoteFinishedResources pageVoteFinishedResources,
            PageHowToVoteResources pageHowToVoteResources,
            PageCandidatesResources pageCandidatesResources,
            PageNumberOfVoteResources pageNumberOfVoteResources,
            PageResultResources pageResultResources,
            PageEndOfVoteResources pageEndOfVoteResources,
            Page404Resources page404Resources,
            PageAddEditResources pageAddEditResources,
            ItemModalboxResources itemModalboxResources){
        appResources.normalize().ensureInjected();
        appResources.styles().ensureInjected();
        appResources.grid().ensureInjected();
        animationsResources.style().ensureInjected();
        pageAlreadyVotedResources.style().ensureInjected();
        pageConfirmVoteResources.style().ensureInjected();
        pageNoVoteResources.style().ensureInjected();
        pageHomeResources.style().ensureInjected();
        pageThanksResources.style().ensureInjected();
        page404Resources.style().ensureInjected();
        pageIdentificationResources.style().ensureInjected();
        pageVoteFinishedResources.style().ensureInjected();
        pageVoteResources.style().ensureInjected();
        pageHowToVoteResources.style().ensureInjected();
        pageCandidatesResources.style().ensureInjected();
        pageNumberOfVoteResources.style().ensureInjected();
        pageResultResources.style().ensureInjected();
        pageEndOfVoteResources.style().ensureInjected();
        pageAddEditResources.style().ensureInjected();
        itemModalboxResources.style().ensureInjected();
    }
}
