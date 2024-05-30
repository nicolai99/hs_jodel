package com.example.jodel.jodel.service;

import java.time.LocalDateTime;
import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jodel.jodel.repository.JodelRepository;
import com.example.jodel.user.service.UserAccountService;
import com.example.jodel.city.service.CityService;
import com.example.jodel.jodel.model.*;

@Service
public class JodelService {
    @Autowired
    JodelRepository rep;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    CityService cityService;

    public Optional<Jodel> getJodelById(long id) {
        return rep.findById(id);
    }

    public List<Jodel> getAllJodel(){
        return rep.findAll();
    }

    public Jodel setJodel(String text, String f_user) {
        Jodel jodel = new Jodel();
        jodel.setF_city(cityService.getCityById(1).orElseThrow());
        jodel.setF_user(userAccountService.getUserAccountByID(f_user).orElseThrow());
        jodel.setText(text);
        LocalDateTime now = LocalDateTime.now();
        jodel.setTimestemp(now);
        return rep.save(jodel);
    }
}
