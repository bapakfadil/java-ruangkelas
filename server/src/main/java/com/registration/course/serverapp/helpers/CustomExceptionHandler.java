package com.registration.course.serverapp.helpers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.registration.course.serverapp.api.dto.response.ResponseData;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ResponseData<String>> handleEmptyResultDataAccessException(
            EmptyResultDataAccessException ex) {
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setStatus(false);
        responseData.getMessages().add(ex.getMessage() + " tidak ditemukan di database");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseData<String>> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setStatus(false);
        responseData.getMessages().add(ex.getMessage() + " sudah ada di database");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseData);
    }
}