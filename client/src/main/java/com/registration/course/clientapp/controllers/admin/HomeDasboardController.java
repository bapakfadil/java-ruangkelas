package com.registration.course.clientapp.controllers.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dasboard")
@PreAuthorize("hasRole('ADMIN')")
public class HomeDasboardController {

  @GetMapping
  public String homeDasboard() {

    return "admin/index";
  }

}
