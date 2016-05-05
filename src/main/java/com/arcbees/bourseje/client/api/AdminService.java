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

package com.arcbees.bourseje.client.api;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.arcbees.bourseje.shared.Candidate;
import com.arcbees.bourseje.shared.CandidateResult;
import com.arcbees.bourseje.shared.Parameters;
import com.arcbees.bourseje.shared.ResourcesPath;
import com.arcbees.bourseje.shared.VoteState;
import com.gwtplatform.dispatch.rest.shared.RestAction;

@Path(ResourcesPath.ADMIN)
public interface AdminService {
    @GET
    @Path(ResourcesPath.CANDIDATE_RESULTS)
    RestAction<Collection<CandidateResult>> getVotesPerCandidate();

    @GET
    @Path(ResourcesPath.WINNER)
    RestAction<CandidateResult> getWinner();

    @PUT
    @Path(ResourcesPath.VOTE_STATE)
    RestAction<Void> setVoteState(VoteState voteState);

    @POST
    @Path(ResourcesPath.CANDIDATE)
    RestAction<Void> addCandidate(Candidate candidate);

    @PUT
    @Path(ResourcesPath.CANDIDATE + ResourcesPath.CANDIDATE_ID)
    RestAction<Void> updateCandidate(@PathParam(Parameters.ID) Long id, Candidate candidate);

    @DELETE
    @Path(ResourcesPath.CANDIDATE + ResourcesPath.CANDIDATE_ID)
    RestAction<Void> removeCandidate(@PathParam(Parameters.ID) Long id);
}
