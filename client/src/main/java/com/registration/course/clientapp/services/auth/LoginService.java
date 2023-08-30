package com.registration.course.clientapp.services.auth;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration.course.clientapp.models.dto.request.LoginRequest;
import com.registration.course.clientapp.models.dto.response.LoginResponse;
import com.registration.course.clientapp.models.dto.response.ResponseData;

@Service
public class LoginService {

  @Autowired
  RestTemplate restTemplate;

  @Value("${server.base.url}/login")
  public String url;

  public ResponseData<LoginResponse> login(LoginRequest loginRequest)
      throws JsonMappingException, JsonProcessingException {
    try {
      ResponseEntity<ResponseData<LoginResponse>> response = restTemplate.exchange(
          url,
          HttpMethod.POST,
          new HttpEntity<LoginRequest>(loginRequest),
          new ParameterizedTypeReference<ResponseData<LoginResponse>>() {
          });

      LoginResponse loginResponse = response.getBody().getPayload().get(0);
      setAuthentication(loginResponse, loginRequest.getPassword());
      return response.getBody();

    } catch (HttpClientErrorException e) {
      if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {

        return new ResponseData<>(
            false,
            Collections.singletonList("Username atau password salah!"),
            null);
      }
      String responseBody = e.getResponseBodyAsString();
      ObjectMapper objectMapper = new ObjectMapper();
      ResponseData<LoginResponse> responseData = objectMapper.readValue(responseBody,
          new TypeReference<ResponseData<LoginResponse>>() {
          });
      return responseData;

    }
  }

  public void setAuthentication(LoginResponse response, String password) {
    Collection<GrantedAuthority> authorities = response.getAuthorities()
        .stream().map(authority -> new SimpleGrantedAuthority(authority))
        .collect(Collectors.toList());

    UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
        response.getUsername(),
        password,
        authorities);
    // ser Principle or session
    SecurityContextHolder.getContext().setAuthentication(userToken);
  }
}
