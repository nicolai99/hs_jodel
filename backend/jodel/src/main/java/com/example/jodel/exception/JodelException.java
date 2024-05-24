package com.example.jodel.exception;

public class JodelException {
    public static class VoteDirectionExists extends Exception {
        public VoteDirectionExists() {
            super("Ein Vote dieser Art ist bereits vorhanden!");

        }
    }

}
