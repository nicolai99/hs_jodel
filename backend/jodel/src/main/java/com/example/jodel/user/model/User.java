package com.example.jodel.user.model;

import jakarta.persistence.*;

@Entity
public class User {

  @Id
    private String id;
    private String name;

}
