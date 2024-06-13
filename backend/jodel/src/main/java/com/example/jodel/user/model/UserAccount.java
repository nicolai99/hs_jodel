package com.example.jodel.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserAccount {

  @Id
  private String id;
  private String name;
}
