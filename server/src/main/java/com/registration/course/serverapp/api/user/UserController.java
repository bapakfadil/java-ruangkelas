package com.registration.course.serverapp.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registration.course.serverapp.api.dto.response.ResponseData;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<ResponseData<User>> getAllUser() {
    ResponseData<User> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("success");
    responseData.setPayload(userService.getAll());
    return ResponseEntity.ok(responseData);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<User>> getUserById(@PathVariable Integer id) {
    ResponseData<User> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("berhasil ditemukan");
    responseData.getPayload().add(userService.getUserbyId(id));
    return ResponseEntity.ok(responseData);
  }

  @GetMapping("username/{name}")
  public ResponseEntity<ResponseData<User>> getUserByName(@PathVariable String name) {
    ResponseData<User> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("berhasil ditemukan");
    responseData.getPayload().add(userService.getByUsername(name));
    return ResponseEntity.ok(responseData);
  }

}
