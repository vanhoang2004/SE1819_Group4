//package com.example.demo.controller;
//
//import com.example.demo.entity.ErrorMessage;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//@RestControllerAdvice
//public class ExceptionHandlers {
//
//    @ExceptionHandler(IndexOutOfBoundsException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorMessage handleIndexOutOfBoundsException(IndexOutOfBoundsException ex, WebRequest request) {
//        return new ErrorMessage(10100, "Đối tượng không tồn tại");
//    }
//}
