package com.example.jodel.jodel.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jodel.jodel.model.Jodel;
import com.example.jodel.jodel.service.JodelService;

@RequestMapping("/jodel/api")
@RestController

public class JodelController {
    @Autowired
    JodelService jodelService;

    @PostMapping("/jodel/setjodel")
    public ResponseEntity<Jodel> setBooking(
            @RequestBody JodelRequest jodelRequest) {
        return ResponseEntity.ok(jodelService.setJodel(jodelRequest.getText(), jodelRequest.getF_user()));
    }

    @GetMapping("jodel/{id}")
    public Jodel requestMethodName(@PathVariable long id) {
        return jodelService.getJodelById(id).orElseThrow();
    }

}
