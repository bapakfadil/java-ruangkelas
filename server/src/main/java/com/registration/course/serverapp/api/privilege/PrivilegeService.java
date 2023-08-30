package com.registration.course.serverapp.api.privilege;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PrivilegeService {

  @Autowired
  private PrivilegeRepository privilegeRepository;

  public List<Privilege> getAll() {
    return privilegeRepository.findAll();
  }

  public Privilege create(Privilege privilege) {
    if (privilegeRepository.existsByName(privilege.getName())) {
      throw new DataIntegrityViolationException("Privilege dengan nama " + privilege.getName());
    }
    return privilegeRepository.save(privilege);
  }

  public Privilege getById(Integer id) {
    return privilegeRepository.findById(id)
        .orElseThrow(() -> new EmptyResultDataAccessException("data privilege tersebut", 0));
  }

  public Privilege delete(Integer id) {
    Privilege privilege = this.getById(id);
    privilegeRepository.delete(privilege);
    return privilege;
  }

  public Privilege update(Integer id, Privilege privilege) {
    if (privilegeRepository.existsByName(privilege.getName())) {
      throw new DataIntegrityViolationException("Privilege dengan nama " + privilege.getName());
    }
    this.getById(id);
    privilege.setId(id);
    return privilegeRepository.save(privilege);
  }

}
