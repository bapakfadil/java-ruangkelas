package com.registration.course.clientapp.helpers;

import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration.course.clientapp.models.dto.response.ResponseData;

public class ResponseErrorFormat<T> {
  public ResponseData<T> format(HttpClientErrorException e)
      throws JsonMappingException, JsonProcessingException {

    String responseBody = e.getResponseBodyAsString();
    ObjectMapper objectMapper = new ObjectMapper();
    ResponseData<T> responseData = objectMapper.readValue(responseBody,
        new TypeReference<ResponseData<T>>() {
        });
    return responseData;
  }
}
