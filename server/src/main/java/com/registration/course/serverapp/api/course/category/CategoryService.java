package com.registration.course.serverapp.api.course.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  public Category create(Category category) {
    if (categoryRepository.existsByName(category.getName())) {
      throw new DataIntegrityViolationException("Category dengan name " + category.getName());
    }
    return categoryRepository.save(category);
  }

  public Category getById(Integer id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new EmptyResultDataAccessException("Data category tersebut", 0));
  }

  public Category update(Integer id, Category category) {
    if (categoryRepository.existsByName(category.getName())) {
      throw new DataIntegrityViolationException("Category dengan nama " + category.getName());
    }
    this.getById(id);
    category.setId(id);
    return categoryRepository.save(category);
  }

  public Category delete(Integer id) {
    Category category = this.getById(id);
    categoryRepository.delete(category);
    return category;
  }
}
