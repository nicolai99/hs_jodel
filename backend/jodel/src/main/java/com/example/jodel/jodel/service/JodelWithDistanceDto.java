package com.example.jodel.jodel.service;

import java.util.List;

import com.example.jodel.jodel.model.Jodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JodelWithDistanceDto {
    private double distance;
    private Jodel jodel;

}
