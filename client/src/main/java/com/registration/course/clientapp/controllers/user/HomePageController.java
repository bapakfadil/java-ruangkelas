package com.registration.course.clientapp.controllers.user;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {

  @GetMapping
  public String landingPage(Model model, Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
      model.addAttribute("auth", true);
      model.addAttribute("authentication", authentication);
      model.addAttribute("isROLE", authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority());
    } else {
      model.addAttribute("auth", false);
    }
    return "user/index";
  }
}
