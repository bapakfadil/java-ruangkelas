package com.registration.course.clientapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.registration.course.clientapp.models.Course;
import com.registration.course.clientapp.models.dto.response.ResponseData;

@Service
public class CourseService {

  @Value("${server.base.url}/course")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public ResponseData<Course> getAll() {
    return restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<ResponseData<Course>>() {
        }).getBody();
  }

  public ResponseData<Course> getById(Integer id) {
    return restTemplate
        .exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<ResponseData<Course>>() {
            })
        .getBody();
  }

  public ResponseData<Course> update(Integer id, Course course) {
    HttpEntity<Course> httpEntity = new HttpEntity<Course>(course);
    return restTemplate
        .exchange(
            url.concat("/" + id),
            HttpMethod.PUT,
            httpEntity,
            new ParameterizedTypeReference<ResponseData<Course>>() {
            })
        .getBody();
  }

  public ResponseData<Course> create(Course course) {
    HttpEntity<Course> httpEntity = new HttpEntity<Course>(course);
    return restTemplate.exchange(
        url,
        HttpMethod.POST,
        httpEntity,
        new ParameterizedTypeReference<ResponseData<Course>>() {
        }).getBody();
  }

}
