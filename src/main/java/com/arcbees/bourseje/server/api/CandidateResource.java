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

package com.arcbees.bourseje.server.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.arcbees.bourseje.server.services.CandidateService;
import com.arcbees.bourseje.shared.Parameters;
import com.arcbees.bourseje.shared.ResourcesPath;

@Path(ResourcesPath.CANDIDATES)
@Produces(MediaType.APPLICATION_JSON)
public class CandidateResource {
    private final CandidateService candidateService;

    @Inject
    public CandidateResource(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GET
    public Response getCandidates() {
        return Response.ok(candidateService.getCandidates()).build();
    }

    @GET
    @Path(ResourcesPath.CANDIDATE_ID)
    public Response getCandidateById(@PathParam(Parameters.ID) Long id) {
        return Response.ok(candidateService.getCandidateById(id)).build();
    }
}
