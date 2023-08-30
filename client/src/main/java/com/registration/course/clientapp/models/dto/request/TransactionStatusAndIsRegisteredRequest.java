package com.registration.course.clientapp.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStatusAndIsRegisteredRequest {
  private String statusUpdate;
}