package com.example.jodel.comment.model;

import java.time.LocalDateTime;
import com.example.jodel.jodel.model.Jodel;
import com.example.jodel.user.model.*;
import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "f_jodel")
    Jodel jodel;

    @OneToOne
    @JoinColumn(name = "f_user")
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

    public void setF_user(UserAccount user) {
        this.user = user;
    }

    public void setF_jodel(Jodel jodel) {
        this.jodel = jodel;
    }

}
