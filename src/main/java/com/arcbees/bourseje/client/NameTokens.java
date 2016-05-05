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

package com.arcbees.bourseje.client;

public class NameTokens {
    public static final String PARAM_ID = "id";

    public static final String HOME = "/home";
    public static final String IDENTIFICATION = "/identification";
    public static final String VOTE = "/vote";
    public static final String CONFIRM_VOTE = "/confirm_vote";
    public static final String THANKS = "/thanks";

    public static final String VOTE_INACTIVE = "/vote_inactive";
    public static final String ALREADY_VOTED = "/already_voted";
    public static final String VOTE_FINISHED = "/vote_finished";
    public static final String PAGE_404 = "/not_found";

    // admin
    public static final String PREFIX_ADMIN = "/admin";
    public static final String ADMIN_DASHBOARD = PREFIX_ADMIN + "/dashboard";
    public static final String WINNER = PREFIX_ADMIN + "/winner";
    public static final String ADD = PREFIX_ADMIN + "/add";
    public static final String EDIT = PREFIX_ADMIN + "/edit";
}
