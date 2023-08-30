package com.registration.course.serverapp.api.role;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registration.course.serverapp.api.dto.response.ResponseData;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/role")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @GetMapping
  public ResponseEntity<ResponseData<Role>> getAllRole() {
    ResponseData<Role> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("success");
    responseData.setPayload(roleService.getAll());
    return ResponseEntity.ok(responseData);
  }

  @PostMapping
  public ResponseEntity<ResponseData<Role>> create(@Validated @RequestBody Role role, Errors errors) {
    ResponseData<Role> responseData = new ResponseData<>();
    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessages().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    responseData.setStatus(true);
    responseData.getPayload().add(roleService.create(role));
    responseData.getMessages().add("Role " + role.getName() + " berhasil ditambahkan");
    return ResponseEntity.ok(responseData);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseData<Role>> update(@Valid @RequestBody Role role, @PathVariable Integer id,
      Errors errors) {
    ResponseData<Role> responseData = new ResponseData<>();
    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessages().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    responseData.setStatus(true);
    responseData.getPayload().add(roleService.update(id, role));
    return ResponseEntity.ok(responseData);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseData<Role>> delete(@PathVariable Integer id) {
    ResponseData<Role> responseData = new ResponseData<>();

    responseData.setStatus(true);
    responseData.getMessages().add("data berhasil dihapus");
    responseData.getPayload().add(roleService.delete(id));
    return ResponseEntity.ok(responseData);
  }
}
