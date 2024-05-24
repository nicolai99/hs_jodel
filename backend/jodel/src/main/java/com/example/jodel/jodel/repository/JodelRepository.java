package com.example.jodel.jodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jodel.jodel.model.*;;

@Repository
public interface JodelRepository extends JpaRepository<Jodel, Long>

{
}