package com.example.jodel.comment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.jodel.comment.model.Comment;
import com.example.jodel.comment.repository.CommentRepository;

public class CommentService {

    @Autowired
    CommentRepository rep;

    Optional<Comment> getCommentById(long id) {
        return rep.findById(id);
    }

}
