package com.example.jodel.jodel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.example.jodel.city.model.*;
import com.example.jodel.user.model.*;

@Getter
@Setter
@Entity
public class Jodel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "f_city")
    City city;

    @ManyToOne
    @JoinColumn(name = "f_user")
    UserAccount user;

    String text;
    LocalDateTime timestemp;
}
