package com.registration.course.serverapp.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

  @NotBlank(message = "username tidak boleh kosong")
  private String username;

  @NotBlank(message = "password tidak boleh kosong")
  private String password;
}
