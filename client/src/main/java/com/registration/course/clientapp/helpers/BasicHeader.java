package com.registration.course.clientapp.helpers;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BasicHeader {
  public static String createBasicToken(String username, String password) {
    String auth = username + ":" + password; // username:password
    byte[] endcodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
    return new String(endcodedAuth);
  }

  public static HttpHeaders getHeader() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return new HttpHeaders() {
      {
        set("Authorization", "Basic " + createBasicToken(auth.getName(), auth.getCredentials().toString()));
      }
    };
  }
}
