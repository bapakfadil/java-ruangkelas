package com.registration.course.serverapp.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserRequest {

  @NotBlank(message = "Nama tidak boleh kosong")
  private String name;

  @NotBlank(message = "Username tidak boleh kosong")
  private String username;

  @NotBlank(message = "Email tidak boleh kosong")
  private String email;

  @NotBlank(message = "Password tidak boleh kosong")
  private String password;
}
