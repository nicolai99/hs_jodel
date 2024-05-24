package com.example.jodel.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jodel.vote.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>

{
}