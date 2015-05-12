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

package com.arcbees.bourseje.server.services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.arcbees.bourseje.server.dao.CurrentVoteStateDao;
import com.arcbees.bourseje.server.dao.VoteItemDao;
import com.arcbees.bourseje.server.exception.AlreadyVotedException;
import com.arcbees.bourseje.server.exception.InactiveVoteException;
import com.arcbees.bourseje.server.exception.NoVoteException;
import com.arcbees.bourseje.server.exception.VoteCodeNotFoundException;
import com.arcbees.bourseje.server.guice.ServerModule;
import com.arcbees.bourseje.server.model.CurrentVoteState;
import com.arcbees.bourseje.shared.CandidateResult;
import com.arcbees.bourseje.shared.VoteItem;
import com.arcbees.bourseje.shared.VoteState;
import com.arcbees.gaestudio.repackaged.com.google.common.collect.Maps;
import com.google.common.base.Function;
import com.google.common.collect.Multimaps;

public class VoteService {
    private final Calendar voteDate;
    private final VoteItemDao voteItemDao;
    private final CurrentVoteStateDao currentVoteStateDao;
    private final FilesService filesService;

    @Inject
    VoteService(
            @Named(ServerModule.VOTE_DATE) Calendar voteDate,
            VoteItemDao voteItemDao,
            CurrentVoteStateDao currentVoteStateDao,
            FilesService filesService) {
        this.voteDate = voteDate;
        this.voteItemDao = voteItemDao;
        this.currentVoteStateDao = currentVoteStateDao;
        this.filesService = filesService;
    }

    public List<VoteItem> getVoteItems() {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        if (today.after(voteDate)) {
            return voteItemDao.getAll();
        } else if (sameDay(today)) {
            throw new InactiveVoteException();
        } else {
            throw new NoVoteException();
        }
    }

    public VoteState getCurrentVoteState() {
        CurrentVoteState currentVoteState = currentVoteStateDao.getCurrentVoteState();

        return currentVoteState == null ? VoteState.INACTIVE : currentVoteState.getState();
    }

    private boolean sameDay(Calendar today) {
        return today.get(Calendar.YEAR) == voteDate.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) == voteDate.get(Calendar.DAY_OF_YEAR);
    }

    public void useCode(String code) {
        verifyCode(code);
    }

    public void vote(VoteItem voteItem, String code) {
        verifyCode(code);

        voteItem.setCode(code);

        voteItemDao.put(voteItem);
    }

    public Collection<CandidateResult> getVotesPerCandidate() {
        List<VoteItem> allVotes = voteItemDao.getAll();
        Map<String, Collection<VoteItem>> groupedVotes = groupByCandidateName(allVotes);

        return createResultsFromGroupedVotes(groupedVotes);
    }

    private Map<String, Collection<VoteItem>> groupByCandidateName(List<VoteItem> votes) {
        return Multimaps.index(votes, new Function<VoteItem, String>() {
            @Override
            public String apply(VoteItem voteItem) {
                return voteItem.getCandidateName();
            }
        }).asMap();
    }

    private Collection<CandidateResult> createResultsFromGroupedVotes(Map<String, Collection<VoteItem>> groupedVotes) {
        return Maps.transformEntries(groupedVotes,
                new Maps.EntryTransformer<String, Collection<VoteItem>, CandidateResult>() {
                    @Override
                    public CandidateResult transformEntry(String candidateName, Collection<VoteItem> voteItems) {
                        return new CandidateResult(candidateName, voteItems.size());
                    }
                }).values();
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
