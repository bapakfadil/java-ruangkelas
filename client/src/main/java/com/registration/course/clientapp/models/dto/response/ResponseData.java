package com.registration.course.clientapp.models.dto.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
  private boolean status;
  private List<String> messages = new ArrayList<>();
  private List<T> payload = new ArrayList<>();
}
