package com.example.jodel.jodel.model;
import jakarta.persistence.*;

@Entity
public class Jodel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    String text;

    

}
