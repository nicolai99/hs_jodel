package com.example.jodel.vote.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.jodel.*;
import com.example.jodel.jodel.model.*;
import com.example.jodel.jodel.service.*;
import com.example.jodel.user.model.*;
import com.example.jodel.user.service.*;
import com.example.jodel.vote.model.Vote;
import com.example.jodel.vote.repository.VoteRepository;

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

    Optional<Vote> getVoteByJodelAndUser(long f_jodel, String f_user) {
        return rep.findByJodelAndUser(f_jodel, f_user);
    }

    Vote setVote(long id, long f_comment, long f_jodel, String f_user, int direction) {

        Jodel jodel = jodelService.getJodelById(f_jodel).orElseThrow(() -> new RuntimeException("Jodel not found"));
        UserAccount user = userAccountService.getUserAccountByID(f_user)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Vote> existingVoteOpt = voteService.getVoteByJodelAndUser(f_jodel, f_user);
        Vote vote;

        if (existingVoteOpt.isPresent()) {
            // Wenn ein Vote existiert, laden Sie diesen
            vote = existingVoteOpt.get();
            if (vote.getDirection() != direction) {
                // Wenn die Direction unterschiedlich ist, aktualisieren Sie diese
                vote.setDirection(direction);
            } else {
                // Wenn die Direction gleich ist, müssen Sie nichts ändern
                return vote;
            }
        } else {
            // Wenn kein Vote existiert, erstellen Sie einen neuen
            vote = new Vote();
            vote.setJodel(jodel);
            vote.setUser(user);
            vote.setDirection(direction);
        }

        // Den Vote speichern (entweder aktualisieren oder neu erstellen)
        return voteService.save(vote);

        return vote;

    }

}
