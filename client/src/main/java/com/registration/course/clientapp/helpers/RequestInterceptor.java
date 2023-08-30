package com.registration.course.clientapp.helpers;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class RequestInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
      throws IOException {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (!request.getURI().getPath().equals("/api/login") && !request.getURI().getPath().equals("/api/register")
        && !request.getURI().getPath().startsWith("/api/course")) {
      request.getHeaders().add("Authorization", "Basic " + BasicHeader.createBasicToken(
          auth.getName(), auth.getCredentials().toString()));
    }

    ClientHttpResponse response = execution.execute(request, body);

    return response;

  }

}
