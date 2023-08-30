package com.registration.course.clientapp.models;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private Integer id;
  private String username;
  private String password;
  private boolean is_enabled;
  private boolean is_account_non_locked;
  private Timestamp created_at;
  private Member member;
  private List<Role> roles;

}
