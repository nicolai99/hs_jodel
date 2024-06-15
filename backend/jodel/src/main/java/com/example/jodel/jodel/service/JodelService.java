package com.example.jodel.jodel.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.AbstractMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jodel.jodel.repository.JodelRepository;
import com.example.jodel.user.service.UserAccountService;
import com.example.jodel.city.model.City;
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

    public List<Jodel> getAllJodel() {

        return rep.findAll();
    }

    public List<JodelWithDistanceDto> getAllJodelWithDistance(double lat, double lon, double maxDistance) {
        List<Jodel> jodels = rep.findAll();

        List<JodelWithDistanceDto> jodelsWithDistance = jodels.stream()
                .map(jodel -> {
                    double distance = cityService.getDistance(lat, lon, jodel.getCity());
                    return new AbstractMap.SimpleEntry<>(jodel, distance);
                })
                .filter(entry -> entry.getValue() <= maxDistance)
                .map(entry -> {
                    JodelWithDistanceDto dto = new JodelWithDistanceDto();
                    dto.setJodel(entry.getKey());
                    dto.setDistance(entry.getValue());
                    return dto;
                })
                .collect(Collectors.toList());

        return jodelsWithDistance;
    }

    public Jodel setJodel(String text, String f_user, double lat, double lon) {
        Jodel jodel = new Jodel();
        jodel.setCity(cityService.setCity(lat, lon));
        jodel.setUser(userAccountService.getUserAccountByID(f_user).orElseThrow());
        jodel.setText(text);
        LocalDateTime now = LocalDateTime.now();
        jodel.setTimestemp(now);
        return rep.save(jodel);
    }
}
