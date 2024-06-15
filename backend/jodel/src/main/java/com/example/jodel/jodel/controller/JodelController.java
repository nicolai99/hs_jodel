package com.example.jodel.jodel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jodel.authentification.NameConverter;
import com.example.jodel.jodel.model.Jodel;
import com.example.jodel.jodel.service.JodelService;
import com.example.jodel.jodel.service.JodelWithDistanceDto;

@RequestMapping("/jodel/api")
@RestController

public class JodelController {
    @Autowired
    JodelService jodelService;

    @PostMapping("/jodel/setjodel")
    public ResponseEntity<Jodel> setBooking(
            @RequestBody JodelRequest jodelRequest, @RequestHeader("Authorization") String authorizationHeader) {
        String id = new NameConverter(authorizationHeader).sub;
        return ResponseEntity.ok(jodelService.setJodel(jodelRequest.getText(), id,
                jodelRequest.getLatitude(), jodelRequest.getLongitude()));
    }

    @GetMapping("/jodel/{id}")
    public Jodel getJodelById(@PathVariable long id) {
        return jodelService.getJodelById(id).orElseThrow();
    }

    @GetMapping("/jodel")
    public List<Jodel> getAllJodel() {
        return jodelService.getAllJodel();
    }

    @GetMapping("/jodelwithdistance")
    public List<JodelWithDistanceDto> getAllJodelWithDistance(@RequestParam double lat, @RequestParam double lon,
            @RequestParam double maxdistance) {
        return jodelService.getAllJodelWithDistance(lat, lon, maxdistance);
    }
}
