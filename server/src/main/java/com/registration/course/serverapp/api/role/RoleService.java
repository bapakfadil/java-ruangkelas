package com.registration.course.serverapp.api.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {

  @Autowired
  RoleRepository roleRepository;

  public List<Role> getAll() {
    return roleRepository.findAll();
  }

  public Role create(Role role) {
    if (roleRepository.existsByName(role.getName())) {
      throw new DataIntegrityViolationException("Role dengan nama " + role.getName());
    }
    return roleRepository.save(role);
  }

  public Role getById(Integer id) {
    return roleRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Data role tersebut", 0));
  }

  public Role update(Integer id, Role role) {
    if (roleRepository.existsByName(role.getName())) {
      throw new DataIntegrityViolationException("Role dengan nama " + role.getName());
    }
    this.getById(id);
    role.setId(id);
    return roleRepository.save(role);
  }

  public Role delete(Integer id) {
    Role role = this.getById(id);
    roleRepository.delete(role);
    return role;
  }
}
