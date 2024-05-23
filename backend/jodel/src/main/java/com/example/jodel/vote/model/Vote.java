package com.example.jodel.vote.model;
import com.example.jodel.comment.model.Comment;
import com.example.jodel.jodel.model.Jodel;

import jakarta.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn (name="f_comment",referencedColumnName = "id")
    Comment comment;

    @ManyToOne
    @JoinColumn (name="f_jodel",referencedColumnName = "id")
    Jodel jodel;

    String f_user;
    private int direction;

    

    

}
