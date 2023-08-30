package com.registration.course.clientapp.models.dto.request;

import lombok.Data;

@Data
public class UserRequest {

  private String name;

  private String username;

  private String email;

  private String password;
}