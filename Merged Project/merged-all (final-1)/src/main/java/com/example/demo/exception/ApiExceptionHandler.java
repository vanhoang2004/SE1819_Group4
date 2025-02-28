package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public String handleApiResquestException(ApiRequestException e, Model model) {

//    public ResponseEntity<Object> handleApiResquestException(ApiRequestException e) {
        //1. create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        //2. return respose entity
        model.addAttribute("errorMessage", e.getMessage());
        return "ExceptionHandle";
        //return new ResponseEntity<>(apiException, badRequest);
    }
}
