package com.example.jodel.jodel.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.jodel.city.model.*;
import com.example.jodel.user.model.*;

@Entity
public class Jodel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "f_city", referencedColumnName = "id")
    City city;

    @ManyToOne
    @JoinColumn(name = "f_user", referencedColumnName = "id")
    UserAccount user;

    String text;
    LocalDateTime timestemp;

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTimestemp() {
        return timestemp;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimestemp(LocalDateTime timestemp) {
        this.timestemp = timestemp;
    }

    public void setF_city(City city) {
        this.city = city;
    }

    public void setF_user(UserAccount user) {
        this.user = user;
    }

}
