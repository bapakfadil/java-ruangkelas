package com.registration.course.clientapp.controllers.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registration.course.clientapp.models.Course;
import com.registration.course.clientapp.services.CourseService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/dasboard/course")
@PreAuthorize("hasRole('ADMIN')")
public class CourseDasboardController {

  private CourseService courseService;

  @GetMapping
  public String getAllCourses(Model model) {
    model.addAttribute("courses", courseService.getAll().getPayload());
    return "admin/course/course";
  }

  @GetMapping("/{id}")
  public String detailPage(@PathVariable Integer id, Model model) {

    model.addAttribute("course", courseService.getById(id).getPayload().get(0));
    return "admin/course/course-detail";
  }

  @GetMapping("/update/{id}")
  public String updatePage(@PathVariable Integer id, Model model) {

    model.addAttribute("course", courseService.getById(id).getPayload().get(0));
    return "admin/course/course-update";
  }

  @PutMapping("/{id}")
  public String updateCourse(@PathVariable Integer id, Course course) {
    courseService.update(id, course);
    return "redirect:/admin/dasboard/course";
  }

  @GetMapping("/create")
  public String createPage(Model model, Course course) {

    return "admin/course/course-create";
  }

  @PostMapping
  public String create(Course course) {
    courseService.create(course);
    return "redirect:/admin/dasboard/course";
  }

}
