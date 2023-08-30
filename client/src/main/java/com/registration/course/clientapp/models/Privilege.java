package com.registration.course.clientapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {

  private Integer id;
  private String name;
  private List<Role> roles;
}
