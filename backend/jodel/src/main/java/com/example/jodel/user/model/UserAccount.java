package com.example.jodel.user.model;

import jakarta.persistence.*;

@Entity
public class UserAccount {

  @Id
  private String id;
  private String name;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
}
