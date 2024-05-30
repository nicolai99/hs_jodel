package com.example.jodel.comment.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jodel.comment.model.Comment;
import com.example.jodel.comment.repository.CommentRepository;
import com.example.jodel.jodel.service.JodelService;
import com.example.jodel.user.service.UserAccountService;

@Service
public class CommentService {

    @Autowired
    CommentRepository rep;

    @Autowired
    JodelService jodelService;

    @Autowired
    UserAccountService userAccountService;

    public Optional<Comment> getCommentById(long id) {
        return rep.findById(id);
    }

    public Comment setComment(String text, long f_jodel, String f_user) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setF_jodel(jodelService.getJodelById(f_jodel).orElseThrow());
        comment.setF_user(userAccountService.getUserAccountByID(f_user).orElseThrow());
        LocalDateTime now = LocalDateTime.now();
        comment.setTimestemp(now);
        return rep.save(comment);
    }

}
