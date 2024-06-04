package com.example.jodel.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jodel.exception.VoteIsAlreadySet;
import com.example.jodel.vote.model.Vote;
import com.example.jodel.vote.model.VoteType;
import com.example.jodel.vote.service.VoteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/jodel/api")
@RestController
public class VoteController {
    @Autowired
    VoteService voteService;

    @PostMapping("/vote/setvote")
    public ResponseEntity<?> setVote(
            @RequestBody VoteRequest voteRequest) {
        try {
            Vote vote = voteService.setVote(voteRequest.getF_entity(), voteRequest.getF_user(),
                    voteRequest.getDirection(), voteRequest.getVoteType());
            return ResponseEntity.ok(vote);
        } catch (VoteIsAlreadySet e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @GetMapping("/vote/getsum/{id}")
    public int getSum(@PathVariable long id, @RequestParam String voteType) {
        VoteType type = validateVoteType(voteType);
        if (type == VoteType.jodel) {
            return voteService.getSumVoteFromJodel(id);
        } else if (type == VoteType.comment) {
            return voteService.getSumVoteFromComment(id);
        }
        throw new RuntimeException("Unexpected error occurred");
    }

    private VoteType validateVoteType(String voteType) {
        try {
            return VoteType.valueOf(voteType.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("VoteType " + voteType + " ist nicht verfügbar");
        }
    }

}
