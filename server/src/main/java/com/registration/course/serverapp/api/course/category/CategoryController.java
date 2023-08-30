package com.registration.course.serverapp.api.course.category;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.registration.course.serverapp.api.dto.response.ResponseData;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/api/course/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<ResponseData<Category>> getAllCategories() {
    ResponseData<Category> responseData = new ResponseData<>();
    responseData.setStatus(true);
    responseData.getMessages().add("success");
    responseData.setPayload(categoryService.getAll());
    return ResponseEntity.ok(responseData);
  }

  @PostMapping
  public ResponseEntity<ResponseData<Category>> createCategory(@Valid @RequestBody Category category, Errors errors) {
    ResponseData<Category> responseData = new ResponseData<>();
    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessages().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    responseData.setStatus(true);
    responseData.getMessages().add("Category berhasil di tambahkan");
    responseData.getPayload().add(categoryService.create(category));
    return ResponseEntity.ok(responseData);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody Category category, @PathVariable Integer id,
      Errors errors) {
    ResponseData<Category> responseData = new ResponseData<>();
    if (errors.hasErrors()) {
      for (ObjectError error : errors.getAllErrors()) {
        responseData.getMessages().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    responseData.setStatus(true);
    responseData.getPayload().add(categoryService.update(id, category));
    return ResponseEntity.ok(responseData);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseData<Category>> deleteCategory(@PathVariable Integer id) {
    ResponseData<Category> responseData = new ResponseData<>();

    responseData.setStatus(true);
    responseData.getMessages().add("Category berhasil dihapus");
    responseData.getPayload().add(categoryService.delete(id));
    return ResponseEntity.ok(responseData);
  }
}
