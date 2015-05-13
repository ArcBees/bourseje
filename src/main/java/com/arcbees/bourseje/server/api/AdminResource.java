/*
 * Copyright 2015 ArcBees Inc.
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.arcbees.bourseje.server.services.AdminService;
import com.arcbees.bourseje.server.services.VoteService;
import com.arcbees.bourseje.shared.ResourcesPath;
import com.arcbees.bourseje.shared.VoteState;

@Path(ResourcesPath.ADMIN)
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {
    private final AdminService adminService;
    private final VoteService voteService;

    @Inject
    AdminResource(
            AdminService adminService,
            VoteService voteService) {
        this.adminService = adminService;
        this.voteService = voteService;
    }

    @GET
    @Path(ResourcesPath.CANDIDATE_RESULTS)
    public Response getVotesPerCandidate() {
        return Response.ok(voteService.getVotesPerCandidate()).build();
    }

    @GET
    @Path(ResourcesPath.WINNER)
    public Response getWinner() {
        return Response.ok(voteService.getWinner()).build();
    }

    @PUT
    @Path(ResourcesPath.VOTE_STATE)
    public Response setVoteState(VoteState voteState) {
        adminService.setVoteState(voteState);

        return Response.ok().build();
    }
}
