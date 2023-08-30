package com.registration.course.serverapp.api.course.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
  public boolean existsByName(String name);
}
