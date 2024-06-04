package com.example.jodel.jodel.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class JodelRequest {

    private String text;
    private String f_user;
    private double latitude;
    private double longitude;

}
