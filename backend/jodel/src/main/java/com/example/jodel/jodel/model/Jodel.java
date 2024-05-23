package com.example.jodel.jodel.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.example.jodel.city.model.*;
import com.example.jodel.user.model.*;

@Entity
public class Jodel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="f_city",referencedColumnName = "id")
    City city;

    
    @ManyToOne
    @JoinColumn (name="f_user",referencedColumnName = "id")
    UserAccount user;

    String text;
    LocalDateTime timestemp;

    

}
