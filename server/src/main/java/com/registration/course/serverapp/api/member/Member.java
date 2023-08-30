package com.registration.course.serverapp.api.member;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.registration.course.serverapp.api.transaction.Transaction;
import com.registration.course.serverapp.api.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_member")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @NotBlank(message = "nama tidak boleh kosong")
  @Column(name = "name", nullable = false)
  private String name;

  @NotBlank(message = "email tidak boleh kosong")
  @Column(name = "email", unique = true)
  private String email;

  private Long phone;
  private String city;
  private String nationaly;
  private Integer activeCourse = 0;

  @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private User user;

  @OneToMany(mappedBy = "member")
  @JsonIgnore
  List<Transaction> transactions;
}
