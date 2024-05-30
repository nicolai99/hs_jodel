package com.example.jodel.vote.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequest {

    private long f_comment;
    private String f_user;
    private long f_jodel;
    private int direction;

}
