package com.registration.course.clientapp.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.registration.course.clientapp.helpers.ResponseErrorFormat;
import com.registration.course.clientapp.models.User;
import com.registration.course.clientapp.models.dto.request.UserRequest;
import com.registration.course.clientapp.models.dto.response.ResponseData;

@Service
public class RegisterService {

  @Autowired
  RestTemplate restTemplate;

  @Value("${server.base.url}/register")
  public String url;

  public ResponseData<User> create(UserRequest userRequest) throws JsonMappingException, JsonProcessingException {

    try {
      ResponseEntity<ResponseData<User>> response = restTemplate.exchange(
          url,
          HttpMethod.POST,
          new HttpEntity<UserRequest>(userRequest),
          new ParameterizedTypeReference<ResponseData<User>>() {
          });

      return response.getBody();

    } catch (HttpClientErrorException e) {
      if (e.getStatusCode() == HttpStatus.CONFLICT) {
        return new ResponseErrorFormat<User>().format(e);
      }
      return new ResponseErrorFormat<User>().format(e);
    }
  }
}
