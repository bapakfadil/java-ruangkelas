package com.registration.course.serverapp.api.transaction.history;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.registration.course.serverapp.api.transaction.Transaction;
import com.registration.course.serverapp.api.transaction.TransactionStatus;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class History {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Timestamp date;

  @Enumerated(EnumType.STRING)
  private TransactionStatus status;
  // private HistoryStatus status;

  @ManyToOne
  @JoinColumn(name = "transaction_id", nullable = false)

  private Transaction transaction;

}
