package com.example.jodel.jodel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jodel.jodel.repository.JodelRepository;
import com.example.jodel.jodel.model.*;

@Service
public class JodelService {
    @Autowired
    JodelRepository rep;

    public Optional<Jodel> getJodelById(long id) {
        return rep.findById(id);
    }
}
