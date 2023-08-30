package com.registration.course.serverapp.api.course;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.registration.course.serverapp.api.course.category.Category;
import com.registration.course.serverapp.api.transaction.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_course")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "thumbnail tidak boleh kosong")
  private String thumbnail;

  @NotBlank(message = "title tidak boleh kosong")
  private String title;

  @NotBlank(message = "instructor tidak boleh kosong")
  private String instructor;

  @NotBlank(message = "description tidak boleh kosong")
  @Lob
  private String description;

  @NotBlank(message = "price tidak boleh kosong")
  private String price;

  @NotBlank(message = "periode tidak boleh kosong")
  private String periode;

  private Date start;
  private Date end;
  private Boolean is_enabled = true;
  private Timestamp created_at;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(mappedBy = "course")
  @JsonIgnore
  List<Transaction> transactions;
}
