package com.registration.course.clientapp.controllers.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.registration.course.clientapp.models.dto.request.LoginRequest;
import com.registration.course.clientapp.models.dto.response.LoginResponse;
import com.registration.course.clientapp.models.dto.response.ResponseData;
import com.registration.course.clientapp.services.auth.LoginService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {

  private LoginService loginService;

  @RequestMapping("/login")
  public String loginPage(LoginRequest loginRequest, Model model, Authentication authentication,
      @ModelAttribute("message") String messages) {
    if (authentication != null && authentication.isAuthenticated()) {
      model.addAttribute("auth", true);
    } else {
      model.addAttribute("message", messages);
      model.addAttribute("auth", false);
    }
    return "auth/login";
  }

  @PostMapping("/login")
  public String login(LoginRequest loginRequest, RedirectAttributes redirectAttributes, Authentication authentication)
      throws JsonMappingException, JsonProcessingException {
    ResponseData<LoginResponse> responseData = loginService.login(loginRequest);

    if (responseData.getPayload().get(0).getAuthorities().get(0).toString().equalsIgnoreCase("ROLE_ADMIN")) {
      return "redirect:/admin/dasboard";

    }

    if (!responseData.isStatus()) {
      redirectAttributes.addAttribute("message", responseData.getMessages());
      return "redirect:/login";
    }

    return "redirect:/";
  }

  @GetMapping("/logout")
  public String logout() {
    SecurityContextHolder.clearContext();
    return "redirect:/login";
  }

}