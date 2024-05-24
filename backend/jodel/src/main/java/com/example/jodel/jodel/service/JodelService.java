package com.example.jodel.jodel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.jodel.jodel.repository.JodelRepository;
import com.example.jodel.jodel.model.*;

public class JodelService {
    @Autowired
    JodelRepository rep;

    Optional<Jodel> getJodelById(long id) {
        return rep.findById(id);
    }
}
