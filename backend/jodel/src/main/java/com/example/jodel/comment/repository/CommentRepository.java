package com.example.jodel.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jodel.comment.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>

{
}