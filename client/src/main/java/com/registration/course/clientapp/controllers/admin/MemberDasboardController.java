package com.registration.course.clientapp.controllers.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registration.course.clientapp.services.MemberService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/dasboard/member")
@PreAuthorize("hasRole('ADMIN')")
public class MemberDasboardController {

  private MemberService memberService;

  @GetMapping
  public String getAllMembers(Model model) {
    model.addAttribute("members", memberService.getAll().getPayload());

    return "admin/member/member";
  }
}
