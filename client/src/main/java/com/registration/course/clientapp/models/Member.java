package com.registration.course.clientapp.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

  private Integer id;
  private String name;
  private String email;
  private Long phone;
  private String city;
  private String nationaly;
  private Integer activeCourse;
  private User user;
  List<Transaction> transactions;

}
