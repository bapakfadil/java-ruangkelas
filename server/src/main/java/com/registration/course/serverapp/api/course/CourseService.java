package com.registration.course.serverapp.api.course;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService {

  @Autowired
  private CourseRepository courseRepository;

  public List<Course> getAll() {
    return courseRepository.findAll();
  }

  public Course create(Course course) {

    LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.systemDefault());
    Timestamp timestamp = Timestamp.valueOf(currentDateTime);
    course.setCreated_at(timestamp);

    return courseRepository.save(course);
  }

  public Course getById(Integer id) {
    return courseRepository.findById(id)
        .orElseThrow(() -> new EmptyResultDataAccessException("Data course tersebut", 0));
  }

  public Course update(Integer id, Course course) {
    this.getById(id);
    course.setId(id);
    return courseRepository.save(course);
  }

  public Course delete(Integer id) {
    Course course = this.getById(id);
    courseRepository.delete(course);
    return course;
  }

}
