package com.example.jodel.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jodel.exception.JodelException;
import com.example.jodel.vote.model.Vote;
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
            Vote vote = voteService.setVote(0, voteRequest.getF_jodel(), voteRequest.getF_user(),
                    voteRequest.getDirection());
            return ResponseEntity.ok(vote);
        } catch (JodelException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @GetMapping("vote/getsum/{id}")
    public int getSum(@PathVariable long id) {
        return voteService.getSumVoteFromJodel(id);
    }

}
