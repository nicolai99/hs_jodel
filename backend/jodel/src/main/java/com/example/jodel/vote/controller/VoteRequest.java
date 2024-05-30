package com.example.jodel.vote.controller;

import com.example.jodel.vote.model.VoteType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequest {

    private long f_entity;
    private String f_user;
    private int direction;
    private VoteType voteType;

}
