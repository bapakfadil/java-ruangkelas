package com.registration.course.serverapp.api.role;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
  public boolean existsByName(String name);
}
