package com.registration.course.clientapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

  private Integer id;
  private String name;
  private List<Privilege> privileges;
  private List<User> users;
}
