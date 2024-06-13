package com.example.jodel.jodel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import com.example.jodel.city.model.*;
import com.example.jodel.comment.model.Comment;
import com.example.jodel.user.model.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToMany(mappedBy = "jodel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("post-comments")
    List<Comment> comments;

    String text;
    LocalDateTime timestemp;

}
