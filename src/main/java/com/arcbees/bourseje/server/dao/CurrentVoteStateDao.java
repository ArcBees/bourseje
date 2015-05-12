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

package com.arcbees.bourseje.server.dao;

import com.arcbees.bourseje.server.model.CurrentVoteState;
import com.arcbees.bourseje.shared.VoteState;

public class CurrentVoteStateDao extends BaseDao<CurrentVoteState> {
    CurrentVoteStateDao() {
        super(CurrentVoteState.class);
    }

    public CurrentVoteState getCurrentVoteState() {
        return get(CurrentVoteState.ID);
    }

    public void setCurrentVoteState(VoteState state) {
        CurrentVoteState currentVoteState = new CurrentVoteState();
        currentVoteState.setState(state);

        put(currentVoteState);
    }
}
