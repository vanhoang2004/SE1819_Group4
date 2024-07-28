package com.example.demo.exception;

//import com.example.demo.entity.ErrorMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handlerFileUploadError(RedirectAttributes ra,Model model){
        System.out.println("Caught file upload error");
//        ra.addFlashAttribute("error","You could not upload file bigger than 20 MB");
        model.addAttribute("error","You could not upload file bigger than 20 MB");
        return "ExceptionHandle";
    }

    @ExceptionHandler(SQLException.class)
    public String handlerSQLError(Model m, SQLException ex){
//        System.out.println("Caught question upload error");
        m.addAttribute("errorMessage", ex.getMessage());
        return "ExceptionHandle";
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
}
