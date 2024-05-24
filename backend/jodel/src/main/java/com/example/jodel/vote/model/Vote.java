package com.example.jodel.vote.model;

import com.example.jodel.comment.model.Comment;
import com.example.jodel.jodel.model.Jodel;
import com.example.jodel.user.model.UserAccount;

import jakarta.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "f_comment", referencedColumnName = "id")
    Comment comment;

    @ManyToOne
    @JoinColumn(name = "f_jodel", referencedColumnName = "id")
    Jodel jodel;

    @ManyToOne
    @JoinColumn(name = "f_user", referencedColumnName = "id")
    UserAccount user;

    private int direction;

    public long getId() {
        return id;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setF_comment(Comment comment) {
        this.comment = comment;
    }

    public void setF_jodel(Jodel jodel) {
        this.jodel = jodel;
    }

    public void setF_user(UserAccount user) {
        this.user = user;
    }

    @Override
    public String toString() {
        String text = "vote{" + "id=" + id + ", direction=" + direction + ",f_jodel=" + jodel.getId() + "}";
        return text;

    }
}
