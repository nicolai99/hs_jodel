package com.example.jodel.vote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jodel.comment.model.Comment;
import com.example.jodel.jodel.model.Jodel;
import com.example.jodel.user.model.UserAccount;
import com.example.jodel.vote.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>

{
    Optional<Vote> findByJodelAndUser(Jodel jodel, UserAccount user);

    Optional<Vote> findByCommentAndUser(Comment comment, UserAccount user);

    @Query(value = "SELECT SUM(v.direction) FROM Vote v WHERE v.f_jodel = ?1", nativeQuery = true)
    Integer sumDirectionByJodelId(Long jodelId);

    @Query(value = "SELECT SUM(v.direction) FROM Vote v WHERE v.f_comment = ?1", nativeQuery = true)
    int sumDirectionByCommentId(Long commentId);
}