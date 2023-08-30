package com.registration.course.clientapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.registration.course.clientapp.models.User;
import com.registration.course.clientapp.models.dto.response.ResponseData;

@Service
public class UserService {
  @Value("${server.base.url}/user")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public ResponseData<User> getByUsername(String username) {
    return restTemplate
        .exchange(
            url.concat("/username/" + username),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<ResponseData<User>>() {
            })
        .getBody();
  }
}
