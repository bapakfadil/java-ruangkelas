package com.registration.course.serverapp.api.transaction;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.registration.course.serverapp.api.course.Course;
import com.registration.course.serverapp.api.member.Member;
import com.registration.course.serverapp.api.transaction.history.History;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_transaction")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "course_id")
  // @JsonProperty(access = JsonProperty.Access.READ_WRITE)
  private Course course;

  @ManyToOne
  @JoinColumn(name = "member_id")
  // @JsonProperty(access = JsonProperty.Access.READ_WRITE)
  private Member member;

  @Enumerated(EnumType.STRING)
  private TransactionStatus status;

  @Column(name = "is_registered")
  private Boolean isRegistered = false;

  private Timestamp created_at;

  @OneToMany(mappedBy = "transaction")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<History> histories;

}
