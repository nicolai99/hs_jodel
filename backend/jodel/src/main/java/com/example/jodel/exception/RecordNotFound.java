package com.example.jodel.exception;

public class RecordNotFound extends Exception {

    public RecordNotFound(String record) {
        super(record + " ist nicht vorhanden");
    }
}
