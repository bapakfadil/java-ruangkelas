package com.registration.course.clientapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.registration.course.clientapp.models.History;
import com.registration.course.clientapp.models.dto.response.ResponseData;

@Service
public class HistoryService {
  @Value("${server.base.url}/history")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public ResponseData<History> getAllHistoryByMemberIdSession() {
    return restTemplate.exchange(
        url.concat("/member"),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<ResponseData<History>>() {
        }).getBody();
  }
}
