package com.example.jodel.vote.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.jodel.exception.*;

import com.example.jodel.jodel.model.*;
import com.example.jodel.jodel.service.*;
import com.example.jodel.user.model.*;
import com.example.jodel.user.service.*;
import com.example.jodel.vote.model.Vote;
import com.example.jodel.vote.repository.VoteRepository;

@Service
public class VoteService {

    @Autowired
    VoteRepository rep;
    @Autowired
    JodelService jodelService;
    @Autowired
    UserAccountService userAccountService;

    Optional<Vote> getVoteById(Long id) {
        return rep.findById(id);
    }

    public int getSumVoteFromJodel(Jodel jodel) {
        long jodelId = jodel.getId();
        return rep.sumDirectionByJodelId(jodelId);
    }

    Optional<Vote> getVoteByJodelAndUser(Jodel jodel, UserAccount user) {
        return rep.findByJodelAndUser(jodel, user);
    }

    public Vote setVote(long f_comment, long f_jodel, String f_user, int direction)
            throws JodelException {

        Jodel jodel = jodelService.getJodelById(f_jodel).orElseThrow(() -> new RuntimeException("Jodel not found"));
        UserAccount user = userAccountService.getUserAccountByID(f_user)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Vote> existingVoteOpt = getVoteByJodelAndUser(jodel, user);
        Vote vote;

        if (existingVoteOpt.isPresent()) {
            // Wenn ein Vote existiert, lade diesen
            vote = existingVoteOpt.get();
            if (vote.getDirection() != direction) {
                // Wenn die Direction unterschiedlich ist, aktualisieren iese
                vote.setDirection(direction);
            } else {
                // Wenn die Direction gleich ist
                throw new JodelException();
            }
        } else {
            // Wenn kein Vote existiert, erstellen Sie einen neuen
            vote = new Vote();
            vote.setF_jodel(jodel);
            vote.setF_user(user);
            vote.setDirection(direction);
        }

        // Den Vote speichern (entweder aktualisieren oder neu erstellen)
        return rep.save(vote);

    }

}
