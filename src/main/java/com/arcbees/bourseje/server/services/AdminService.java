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

package com.arcbees.bourseje.server.services;

import javax.inject.Inject;

import com.arcbees.bourseje.server.dao.CandidateDao;
import com.arcbees.bourseje.server.dao.CurrentVoteStateDao;
import com.arcbees.bourseje.server.dao.VoteItemDao;
import com.arcbees.bourseje.shared.Candidate;
import com.arcbees.bourseje.shared.VoteState;

public class AdminService {
    private final VoteItemDao voteItemDao;
    private final VoteService voteService;
    private final CurrentVoteStateDao currentVoteStateDao;
    private final CandidateDao candidateDao;

    @Inject
    AdminService(
            VoteItemDao voteItemDao,
            VoteService voteService,
            CurrentVoteStateDao currentVoteStateDao,
            CandidateDao candidateDao) {
        this.voteItemDao = voteItemDao;
        this.voteService = voteService;
        this.currentVoteStateDao = currentVoteStateDao;
        this.candidateDao = candidateDao;
    }

    public void setVoteState(VoteState voteState) {
        if (voteState == VoteState.STARTED) {
            clearVotesIfNotAlreadyStarted();
        }

        currentVoteStateDao.setCurrentVoteState(voteState);
    }

    private void clearVotesIfNotAlreadyStarted() {
        if (voteService.getCurrentVoteState() != VoteState.STARTED) {
            voteItemDao.deleteAll();
        }
    }

    public void addCandidate(Candidate candidate) {
        candidateDao.put(candidate);
    }

    public void updateCandidate(Long id, Candidate candidate) {
        Candidate modifiedCandidate = candidateDao.get(id);

        modifiedCandidate.setPicture(candidate.getPicture());
        modifiedCandidate.setName(candidate.getName());
        modifiedCandidate.setCompany(candidate.getCompany());

        candidateDao.put(modifiedCandidate);
    }

    public void removeCandidate(Long id) {
        Candidate candidateToDelete = candidateDao.get(id);

        candidateDao.delete(candidateToDelete);
    }
}
