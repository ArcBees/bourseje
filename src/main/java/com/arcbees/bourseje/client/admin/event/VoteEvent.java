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

package com.arcbees.bourseje.client.admin.event;

import com.arcbees.bourseje.shared.Candidate;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class VoteEvent extends GwtEvent<VoteEventHandler> {
    public static final Type<VoteEventHandler> TYPE = new Type<>();

    private final Candidate candidate;

    public VoteEvent(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public Type<VoteEventHandler> getAssociatedType() {
        return TYPE;
}

    public static void fire(HasHandlers source, Candidate candidate) {
        source.fireEvent(new VoteEvent(candidate));
    }

    @Override
    protected void dispatch(VoteEventHandler handler) {
        handler.onVote(this, candidate);
    }
}
