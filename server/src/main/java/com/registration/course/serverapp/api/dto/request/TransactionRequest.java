package com.registration.course.serverapp.api.dto.request;

import lombok.Data;

@Data
public class TransactionRequest {
  private Integer courseId;
  private Integer memberId;
}
