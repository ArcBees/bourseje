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

package com.arcbees.bourseje.client.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.arcbees.bourseje.shared.ResourcesPath;
import com.arcbees.bourseje.shared.VoteItem;
import com.arcbees.bourseje.shared.VoteState;
import com.gwtplatform.dispatch.rest.shared.RestAction;

@Path(ResourcesPath.VOTE_ITEMS)
public interface VoteService {
    @GET
    @Path(ResourcesPath.CURRENT_VOTE_STATE)
    RestAction<VoteState> getCurrentVoteState();

    @POST
    @Path(ResourcesPath.CODE)
    RestAction<Void> useCode(String code);

    @POST
    RestAction<Void> vote(VoteItem voteItem);
}
