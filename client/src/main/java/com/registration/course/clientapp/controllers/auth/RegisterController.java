package com.registration.course.clientapp.controllers.auth;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.registration.course.clientapp.models.User;
import com.registration.course.clientapp.models.dto.request.UserRequest;
import com.registration.course.clientapp.models.dto.response.ResponseData;
import com.registration.course.clientapp.services.auth.RegisterService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RegisterController {

  private RegisterService registerService;

  @GetMapping("/register")
  public String registerPage(UserRequest userRequest, Model model, Authentication authentication, @ModelAttribute("message") String errorMessage) {
    if (authentication != null && authentication.isAuthenticated()) {
      model.addAttribute("auth", true);
    } else {
      model.addAttribute("message", errorMessage);
      model.addAttribute("auth", false);
    }
    return "auth/register";
  }

  @PostMapping("/register")
  public String register(UserRequest userRequest, RedirectAttributes redirectAttributes)
      throws JsonMappingException, JsonProcessingException {
    ResponseData<User> responseData = registerService.create(userRequest);
    System.out.println(responseData);
    if (!responseData.isStatus()) {
      redirectAttributes.addAttribute("message", responseData.getMessages());
      return "redirect:/register";
    }
    return "redirect:/";
  }

}