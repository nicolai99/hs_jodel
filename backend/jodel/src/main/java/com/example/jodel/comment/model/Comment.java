package com.example.jodel.comment.model;

import java.time.LocalDateTime;

import com.example.jodel.jodel.model.Jodel;
import com.example.jodel.user.model.*;
import jakarta.persistence.*;

@Entity
public class Comment {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn (name="f_jodel",referencedColumnName = "id")
    Jodel jodel;

    @OneToOne
    @JoinColumn (name="f_user",referencedColumnName = "id")
    UserAccount user;

    String text;
    LocalDateTime timestemp;
    
}
