package com.registration.course.clientapp.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

  private Integer id;
  private String thumbnail;
  private String title;
  private String instructor;
  private String description;
  private String price;
  private String periode;
  private Date start;
  private Date end;
  private Boolean is_enabled = true;
  private Timestamp created_at;
  private Category category;
  List<Transaction> transactions;
}
