/*
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

package com.arcbees.bourseje.shared;

import static com.arcbees.bourseje.shared.Parameters.ID;

public class ResourcesPath {
    // vote
    public static final String VOTE_ITEMS = "/voteitems";
    public static final String CURRENT_VOTE_STATE = "/current-state";
    public static final String CODE = "/code";
    public static final String CANDIDATES = "/candidates";
    public static final String CANDIDATE_ID = "/{" + ID + "}";

    // admin
    public static final String ADMIN = "/admin";
    public static final String VOTE_STATE = "/vote-state";
    public static final String CANDIDATE_RESULTS = "/candidate-results";
    public static final String CANDIDATE = "/candidate";

    // login
    public static final String LOGIN = "/login";
    public static final String URL = "/url";
    public static final String WINNER = "/winner";
}
