package com.example.jodel.comment.model;

import java.time.LocalDateTime;
import com.example.jodel.jodel.model.Jodel;
import com.example.jodel.user.model.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonBackReference("post-comments")
    @JoinColumn(name = "f_jodel")
    Jodel jodel;

    @ManyToOne
    @JoinColumn(name = "f_user")
    UserAccount user;

    String text;
    LocalDateTime timestemp;

}
