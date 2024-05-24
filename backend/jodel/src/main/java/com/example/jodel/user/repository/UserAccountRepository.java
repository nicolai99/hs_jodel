package com.example.jodel.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jodel.user.model.*;;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String>

{
}