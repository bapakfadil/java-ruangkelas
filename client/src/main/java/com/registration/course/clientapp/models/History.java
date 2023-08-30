package com.registration.course.clientapp.models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

  private Integer id;
  private Timestamp date;
  private TransactionStatus status;
  private Transaction transaction;

}