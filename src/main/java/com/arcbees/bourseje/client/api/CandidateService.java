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

package com.arcbees.bourseje.client.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.arcbees.bourseje.shared.Candidate;
import com.arcbees.bourseje.shared.Parameters;
import com.arcbees.bourseje.shared.ResourcesPath;
import com.gwtplatform.dispatch.rest.shared.RestAction;

@Path(ResourcesPath.CANDIDATES)
public interface CandidateService {
    @GET
    RestAction<List<Candidate>> getCandidates();

    @GET
    @Path(ResourcesPath.CANDIDATE_ID)
    RestAction<Candidate> getCandidateById(@PathParam(Parameters.ID) Long id);
}
