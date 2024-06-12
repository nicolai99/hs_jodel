package com.example.jodel.vote.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jodel.comment.model.Comment;
import com.example.jodel.comment.service.CommentService;
import com.example.jodel.exception.*;

import com.example.jodel.jodel.model.*;
import com.example.jodel.jodel.service.*;
import com.example.jodel.user.model.*;
import com.example.jodel.user.service.*;
import com.example.jodel.vote.model.Vote;
import com.example.jodel.vote.model.VoteType;
import com.example.jodel.vote.repository.VoteRepository;

@Service
public class VoteService {

    @Autowired
    VoteRepository rep;
    @Autowired
    JodelService jodelService;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    CommentService commentService;

    Optional<Vote> getVoteById(Long id) {
        return rep.findById(id);
    }

    public int getSumVoteFromJodel(long jodelId) {
        Integer sum = rep.sumDirectionByJodelId(jodelId);
        return sum != null ? sum : 0;
    }

    public int getSumVoteFromComment(long commentId) {
        return rep.sumDirectionByCommentId(commentId);
    }

    public Optional<Vote> getVoteByJodelAndUser(Jodel jodel, UserAccount user) {
        return rep.findByJodelAndUser(jodel, user);
    }

    public Optional<Vote> getVoteByCommentAndUser(Comment comment, UserAccount user) {
        return rep.findByCommentAndUser(comment, user);
    }

    public Vote writeExistingOrNew(Optional<Vote> existingVoteOpt, int direction, UserAccount user, Jodel jodel,
            Comment comment, VoteType voteType) throws VoteIsAlreadySet {
        Vote vote;

        if (existingVoteOpt.isPresent()) {
            // Wenn ein Vote existiert, lade diesen
            vote = existingVoteOpt.get();
            if (vote.getDirection() != direction) {
                // Wenn die Direction unterschiedlich ist, aktualisieren diese
                vote.setDirection(direction);
            } else {
                // Wenn die Direction gleich ist
                throw new VoteIsAlreadySet();
            }
        } else {
            // Wenn kein Vote existiert, erstellen Sie einen neuen
            vote = new Vote();
            vote.setF_user(user);
            if (voteType == VoteType.jodel) {
                vote.setF_jodel(jodel);
            } else if (voteType == VoteType.comment) {
                vote.setF_comment(comment);
            }
            vote.setDirection(direction);
        }

        // Den Vote speichern (entweder aktualisieren oder neu erstellen)
        return rep.save(vote);
    }

    public Vote setVote(long entityId, String userId, int direction, VoteType voteType) throws VoteIsAlreadySet {
        UserAccount user = userAccountService.getUserAccountByID(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Vote> existingVoteOpt;
        Jodel jodel = null;
        Comment comment = null;

        if (voteType == VoteType.jodel) {
            jodel = jodelService.getJodelById(entityId).orElseThrow(() -> new RuntimeException("Jodel not found"));
            existingVoteOpt = getVoteByJodelAndUser(jodel, user);
        } else if (voteType == VoteType.comment) {
            comment = commentService.getCommentById(entityId)
                    .orElseThrow(() -> new RuntimeException("Comment not found"));
            existingVoteOpt = getVoteByCommentAndUser(comment, user);
        } else {
            throw new RuntimeException("Invalid VoteType");
        }

        return writeExistingOrNew(existingVoteOpt, direction, user, jodel, comment, voteType);
    }

}
