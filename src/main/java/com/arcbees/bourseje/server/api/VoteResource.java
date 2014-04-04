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

package com.arcbees.bourseje.server.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.arcbees.bourseje.server.services.VoteService;
import com.arcbees.bourseje.shared.ResourcesPath;

@Path(ResourcesPath.VOTE_ITEMS)
@Produces(MediaType.APPLICATION_JSON)
public class VoteResource {
    private final VoteService voteService;

    @Inject
    VoteResource(
            VoteService voteService) {
        this.voteService = voteService;
    }

    @GET
    public Response getPollItems() {
        return Response.ok(voteService.getPollItems()).build();
    }
}
