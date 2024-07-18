package com.example.demo.controller;

//import com.example.demo.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlers {

//    @ExceptionHandler(IndexOutOfBoundsException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorMessage handleIndexOutOfBoundsException(IndexOutOfBoundsException ex, WebRequest request) {
//        return new ErrorMessage(10100, "Đối tượng không tồn tại");
//    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handlerFileUploadError(RedirectAttributes ra){
        System.out.println("Caught file upload error");
        ra.addFlashAttribute("error","You could not upload file bigger than 20 MB");
        return "redirect:/test/materials";
    }
    @ExceptionHandler(SQLException.class)
    public String handlerSQLError(RedirectAttributes ra){
        System.out.println("Caught question upload error");
        ra.addFlashAttribute("error","Answer must be one of the options");
        return "redirect:/test/questionbank";

    }

    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException ex, RedirectAttributes redirectAttributes, Model model) {
//        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        //redirectAttributes.addFlashAttribute("errorMessage", "kho có gì");
        model.addAttribute("errorMessage", ex.getMessage());

        return "ExceptionHandle";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {

        model.addAttribute("errorMessage", ex.getMessage());
        return "ExceptionHandle";
    }
//    @ExceptionHandler(ApiRequestException.class)
//    public String handleApiRequestException(ApiRequestException ex, Model model) {
//        model.addAttribute("errorMessage", ex.getMessage());
//        return "ExceptionHandle";
//    }

}
