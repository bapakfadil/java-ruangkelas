package com.registration.course.serverapp.api.member;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registration.course.serverapp.api.dto.response.ResponseData;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

  @Autowired
  MemberService memberService;

  @GetMapping
  public ResponseEntity<ResponseData<Member>> getAllMembers() {
    ResponseData<Member> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("success");
    responseData.setPayload(memberService.getAll());
    return ResponseEntity.ok(responseData);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<Member>> getUserById(@PathVariable Integer id) {
    ResponseData<Member> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("berhasil ditemukan");
    responseData.getPayload().add(memberService.getById(id));
    return ResponseEntity.ok(responseData);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseData<Member>> updateMember(@Valid @RequestBody Member member, @PathVariable Integer id,
      Errors errors) {
    ResponseData<Member> responseData = new ResponseData<>();
    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessages().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    responseData.getPayload().add(memberService.update(id, member));
    responseData.setStatus(true);
    responseData.getMessages().add("member berhasil diperbarui");
    return ResponseEntity.ok(responseData);
  }
}
