package com.example.jodel.exception;

public class VoteIsAlreadySet extends Exception {
    public VoteIsAlreadySet() {
        super("Ein Vote dieser Art ist bereits vorhanden!");
    }
}
