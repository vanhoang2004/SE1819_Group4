package com.example.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code");

        if(status != null){
            HttpStatus httpStatus = HttpStatus.resolve((int) status);
            if (httpStatus == HttpStatus.NOT_FOUND) {
                String errorMessage = "Oops, no such page found!";
                ApiException apiException = new ApiException(
                        errorMessage,
                        HttpStatus.NOT_FOUND,
                        ZonedDateTime.now(ZoneId.of("Z"))
                );

                model.addAttribute("errorMessage", errorMessage);
                model.addAttribute("timestamp", apiException.getTimesptamp());
                model.addAttribute("status", apiException.getHttpStatus().value());
                return "ExceptionHandle"; // Your error view page
            }
        }

        // Handle other statuses or default error
        String errorMessage = "An error occurred";
        model.addAttribute("errorMessage", errorMessage);
        return "ExceptionHandle";
    }
}
