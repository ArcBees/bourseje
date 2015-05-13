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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.arcbees.bourseje.server.exception.VoteCodeNotSetException;
import com.arcbees.bourseje.server.services.VoteService;
import com.arcbees.bourseje.shared.CookieNames;
import com.arcbees.bourseje.shared.ResourcesPath;
import com.arcbees.bourseje.shared.VoteItem;

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
    @Path(ResourcesPath.CURRENT_VOTE_STATE)
    public Response getCurrentVoteState() {
        return Response.ok(voteService.getCurrentVoteState()).build();
    }

    @POST
    @Path(ResourcesPath.CODE)
    public Response useCode(String code, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        code = code.replaceAll("\"", ""); // Workaround for serialization leftover quotes

        voteService.useCode(code);

        return Response.ok().build();
    }

    @POST
    public Response vote(VoteItem voteItem, @Context HttpServletRequest request,
                         @Context HttpServletResponse response) {
        String code = getCodeFromCookies(request.getCookies());

        voteService.vote(voteItem, code);

        return Response.ok().build();
    }

    private String getCodeFromCookies(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CookieNames.VOTE_CODE)) {
                    return cookie.getValue();
                }
            }
        }

        throw new VoteCodeNotSetException();
    }
}
