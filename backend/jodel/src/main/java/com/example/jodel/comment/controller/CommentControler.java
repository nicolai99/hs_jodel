package com.example.jodel.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jodel.comment.service.CommentService;
import com.example.jodel.authentification.NameConverter;
import com.example.jodel.comment.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RequestMapping("/jodel/api")
@RestController
public class CommentControler {

    @Autowired
    CommentService commentService;

    @GetMapping("/comment")
    public List<Comment> getAllComment() {
        return commentService.getAllComment();
    }

    @PostMapping("/comment")
    public ResponseEntity<Comment> setBooking(
            @RequestBody CommentRequest commentRequest, @RequestHeader("Authorization") String authorizationHeader) {
        String id = new NameConverter(authorizationHeader).sub;
        return ResponseEntity.ok(
                commentService.setComment(commentRequest.getText(), commentRequest.getF_jodel(),
                        id));
    }

}
