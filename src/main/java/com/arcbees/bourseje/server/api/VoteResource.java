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

import com.arcbees.bourseje.server.exception.AlreadyVotedException;
import com.arcbees.bourseje.server.services.VoteService;
import com.arcbees.bourseje.shared.ResourcesPath;
import com.arcbees.bourseje.shared.VoteItem;

@Path(ResourcesPath.VOTE_ITEMS)
@Produces(MediaType.APPLICATION_JSON)
public class VoteResource {
    private final static String COOKIE_NAME = "voted";

    private final VoteService voteService;

    @Inject
    VoteResource(
            VoteService voteService) {
        this.voteService = voteService;
    }

    @GET
    public Response getVoteItems() {
        return Response.ok(voteService.getVoteItems()).build();
    }

    @POST
    public Response vote(VoteItem voteItem, @Context HttpServletRequest request,
                         @Context HttpServletResponse response) {
        if (alreadyVoted(request.getCookies())) {
            throw new AlreadyVotedException();
        } else {
            Cookie cookie = new Cookie(COOKIE_NAME, "");
            response.addCookie(cookie);
        }

        voteItem.setIp(request.getRemoteAddr());

        voteService.vote(voteItem);

        return Response.ok().build();
    }

    private boolean alreadyVoted(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    return true;
                }
            }
        }

        return false;
    }
}
