package com.registration.course.serverapp.api.privilege;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
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
@RequestMapping("/api/privilege")
public class privilegeController {

  @Autowired
  private PrivilegeService privilegeService;

  @GetMapping
  public ResponseEntity<ResponseData<Privilege>> getAllData() {
    ResponseData<Privilege> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("success");
    responseData.setPayload(privilegeService.getAll());
    return ResponseEntity.ok(responseData);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<Privilege>> getById(@PathVariable Integer id) {
    ResponseData<Privilege> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("success");
    responseData.getPayload().add(privilegeService.getById(id));

    return ResponseEntity.ok(responseData);
  }

  @PostMapping
  public ResponseEntity<ResponseData<Privilege>> create(@Valid @RequestBody Privilege privilege, Errors errors) {
    ResponseData<Privilege> responseData = new ResponseData<>();

    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessages().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    responseData.setStatus(true);
    responseData.getMessages().add("Privilege " + privilege.getName() + " berhasil di tambahkan");
    responseData.getPayload().add(privilegeService.create(privilege));
    return ResponseEntity.ok(responseData);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseData<Privilege>> update(@Valid @RequestBody Privilege privilege,
      @PathVariable Integer id, Errors errors) {
    ResponseData<Privilege> responseData = new ResponseData<>();

    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessages().add(error.getDefaultMessage());
      }

      responseData.setStatus(false);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    responseData.setStatus(true);
    responseData.getMessages().add("berhasil diedit");
    responseData.getPayload().add(privilegeService.update(id, privilege));
    return ResponseEntity.ok(responseData);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseData<Privilege>> delete(@PathVariable Integer id) {
    ResponseData<Privilege> responseData = new ResponseData<>();

    responseData.setStatus(true);
    responseData.getMessages().add("data berhasil dihapus");
    responseData.getPayload().add(privilegeService.delete(id));

    return ResponseEntity.ok(responseData);

  }

}
