package com.example.jodel.comment.model;

import jakarta.persistence.*;

@Entity
public class Comment {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    String text;
    
}
