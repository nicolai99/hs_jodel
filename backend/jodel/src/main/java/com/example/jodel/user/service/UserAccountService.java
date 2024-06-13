package com.example.jodel.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jodel.authentification.NameConverter;
import com.example.jodel.user.model.UserAccount;
import com.example.jodel.user.repository.UserAccountRepository;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository rep;

    public Optional<UserAccount> getUserAccountByID(String id) {
        return rep.findById(id);
    }

    public void setUserAccount(String header) {
        NameConverter attributes = new NameConverter(header);
        String id = attributes.sub;
        String name = attributes.name;

        Optional<UserAccount> existingUser = getUserAccountByID(id);
        if (existingUser.isEmpty()) {
            System.out.println("User wird " + name + " angelegt");
            UserAccount user = new UserAccount();
            user.setId(id);
            user.setName(name);
            rep.save(user);
        }
    }

}
