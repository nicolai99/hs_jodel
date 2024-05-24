package com.example.jodel.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.jodel.user.model.UserAccount;
import com.example.jodel.user.repository.UserAccountRepository;

public class UserAccountService {

    @Autowired
    UserAccountRepository rep;

    Optional<UserAccount> getUserAccountByID(String id) {
        return rep.findById(id);
    }

}
