package com.registration.course.clientapp.models;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

  private Integer id;
  private Course course;
  private Member member;
  private TransactionStatus status;
  private Boolean isRegistered = false;
  private Timestamp created_at;
  private List<History> histories;

}