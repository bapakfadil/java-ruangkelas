package com.registration.course.serverapp.api.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {

  @Autowired
  MemberRespository memberRespository;

  public List<Member> getAll() {
    return memberRespository.findAll();
  }

  public Member getById(Integer id) {
    return memberRespository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("member ", 0));
  }

  public Member update(Integer id, Member member) {
    Member chekingMember = this.getById(id);
    if (!chekingMember.getEmail().equalsIgnoreCase(member.getEmail())) {
      if (memberRespository.existsByEmail(member.getEmail())) {
        throw new DataIntegrityViolationException("Email");
      }
    }
    member.setId(id);
    return memberRespository.save(member);
  }

  public Member updateCourseActiveById(Integer Id) {
    Member member = this.getById(Id);
    member.setId(Id);
    Integer currentActiveCourse = member.getActiveCourse();
    member.setActiveCourse(currentActiveCourse + 1);
    return memberRespository.save(member);
  }

}
