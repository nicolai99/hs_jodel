package com.example.jodel.city.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jodel.city.model.City;
import com.example.jodel.city.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/jodel/api")
@RestController
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping("/city")
    public List<City> getAllCity() {
        return cityService.getAllCity();
    }

}
