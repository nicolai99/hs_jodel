package com.example.jodel.city.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jodel.city.model.City;
import com.example.jodel.city.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    CityRepository rep;

    public Optional<City> getCityById(long id) {
        return rep.findById(id);
    }

}
