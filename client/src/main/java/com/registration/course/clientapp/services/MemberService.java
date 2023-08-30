package com.registration.course.clientapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.registration.course.clientapp.models.Member;
import com.registration.course.clientapp.models.dto.response.ResponseData;

@Service
public class MemberService {
  @Value("${server.base.url}/member")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public ResponseData<Member> getAll() {
    return restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<ResponseData<Member>>() {
        }).getBody();
  }
}
