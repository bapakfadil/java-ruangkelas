package com.registration.course.clientapp.controllers.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registration.course.clientapp.models.dto.request.TransactionRequest;
import com.registration.course.clientapp.services.CourseService;
import com.registration.course.clientapp.services.TransactionService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/courses")
public class CoursesPageController {

  private CourseService courseService;
  private TransactionService transactionService;

  @GetMapping
  public String getAllCourses(Model model, Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
      model.addAttribute("auth", true);
      model.addAttribute("authentication", authentication);
      model.addAttribute("courses", courseService.getAll().getPayload()); // get is not registered
      model.addAttribute("isROLE", authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority());
    } else {
      model.addAttribute("auth", false);
      model.addAttribute("courses", courseService.getAll().getPayload());
    }
    return "user/courses/courses";
  }

  @GetMapping("/{id}")
  public String detailCourse(@PathVariable Integer id, Model model, Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
      model.addAttribute("auth", true);
      model.addAttribute("authentication", authentication);
      model.addAttribute("course", courseService.getById(id).getPayload().get(0));// idem
      model.addAttribute("isROLE", authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority());
    } else {
      model.addAttribute("course", courseService.getById(id).getPayload().get(0));
      model.addAttribute("auth", false);
    }
    return "user/courses/course-details";
  }

  // @PreAuthorize("hasRole('USER')")
  @PostMapping("/{id}")
  public String CreateNewTransactionCourse(@PathVariable Integer id, TransactionRequest transactionRequest,
      Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
      transactionRequest.setCourseId(id);
      transactionService.createTransaction(transactionRequest);
      return "redirect:/user/dasboard/transaction";
    }
    return "redirect:/login";

  }
}
