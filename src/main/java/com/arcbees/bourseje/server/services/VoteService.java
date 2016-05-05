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

package com.arcbees.bourseje.server.services;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.arcbees.bourseje.server.dao.CurrentVoteStateDao;
import com.arcbees.bourseje.server.dao.VoteItemDao;
import com.arcbees.bourseje.server.exception.AlreadyVotedException;
import com.arcbees.bourseje.server.exception.InactiveVoteException;
import com.arcbees.bourseje.server.exception.VoteCodeNotFoundException;
import com.arcbees.bourseje.server.model.CurrentVoteState;
import com.arcbees.bourseje.shared.CandidateResult;
import com.arcbees.bourseje.shared.VoteItem;
import com.arcbees.bourseje.shared.VoteState;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;

public class VoteService {
    private final VoteItemDao voteItemDao;
    private final CurrentVoteStateDao currentVoteStateDao;
    private final FilesService filesService;

    @Inject
    VoteService(
            VoteItemDao voteItemDao,
            CurrentVoteStateDao currentVoteStateDao,
            FilesService filesService) {
        this.voteItemDao = voteItemDao;
        this.currentVoteStateDao = currentVoteStateDao;
        this.filesService = filesService;
    }

    public VoteState getCurrentVoteState() {
        CurrentVoteState currentVoteState = currentVoteStateDao.getCurrentVoteState();

        return currentVoteState == null ? VoteState.INACTIVE : currentVoteState.getState();
    }

    public void useCode(String code) {
        verifyCode(code);
    }

    public void vote(VoteItem voteItem, String code) {
        verifyVoteIsActive();

        verifyCode(code);

        voteItem.setCode(code);

        voteItemDao.put(voteItem);
    }

    private void verifyVoteIsActive() {
        if (getCurrentVoteState() != VoteState.STARTED) {
            throw new InactiveVoteException();
        }
    }

    public Collection<CandidateResult> getVotesPerCandidate() {
        List<VoteItem> allVotes = voteItemDao.getAll();
        Map<Long, Collection<VoteItem>> groupedVotes = groupByCandidateId(allVotes);

        return createResultsFromGroupedVotes(groupedVotes);
    }

    private Map<Long, Collection<VoteItem>> groupByCandidateId(List<VoteItem> votes) {
        return Multimaps.index(votes, new Function<VoteItem, Long>() {
            @Override
            public Long apply(VoteItem voteItem) {
                return voteItem.getCandidateId();
            }
        }).asMap();
    }

    private Collection<CandidateResult> createResultsFromGroupedVotes(Map<Long, Collection<VoteItem>> groupedVotes) {
        return Maps.transformEntries(groupedVotes,
                new Maps.EntryTransformer<Long, Collection<VoteItem>, CandidateResult>() {
                    @Override
                    public CandidateResult transformEntry(Long candidateId, Collection<VoteItem> voteItems) {
                        return new CandidateResult(candidateId, voteItems.size());
                    }
                }).values();
    }

    public CandidateResult getWinner() {
        return Collections.max(getVotesPerCandidate(), new Comparator<CandidateResult>() {
            @Override
            public int compare(CandidateResult c1, CandidateResult c2) {
                return Integer.compare(c1.getNumberOfVotes(), c2.getNumberOfVotes());
            }
        });
    }

    private void verifyCode(String code) {
        if (isCodeAlreadyUsed(code)) {
            throw new AlreadyVotedException();
        } else if (!isCodeValid(code)) {
            throw new VoteCodeNotFoundException();
        }
    }

    private boolean isCodeValid(String code) {
        return filesService.getVoteCodes().contains(code);
    }

    private boolean isCodeAlreadyUsed(String code) {
        return voteItemDao.findByVoteCode(code) != null;
    }
}
