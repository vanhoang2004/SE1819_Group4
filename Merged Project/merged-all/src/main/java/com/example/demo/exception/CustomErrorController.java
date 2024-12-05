//package com.example.demo.exception;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//
//@Controller
//public class CustomErrorController implements ErrorController {
//
////    @RequestMapping("/error")
////    public String handleError(HttpServletRequest request, Model model) {
////        Object status = request.getAttribute("javax.servlet.error.status_code");
////        HttpStatus httpStatus = HttpStatus.resolve((int) status);
////
////        if (httpStatus == HttpStatus.NOT_FOUND) {
////            String errorMessage = "Oops, no such page found!";
////            ApiException apiException = new ApiException(
////                    errorMessage,
////                    HttpStatus.NOT_FOUND,
////                    ZonedDateTime.now(ZoneId.of("Z"))
////            );
////
////            model.addAttribute("errorMessage", errorMessage);
////            model.addAttribute("timestamp", apiException.getTimesptamp());
////            model.addAttribute("status", apiException.getHttpStatus().value());
////            return "ExceptionHandle"; // Your error view page
////        }
////
////        // Handle other statuses or default error
////        String errorMessage = "An error occurred";
////        model.addAttribute("errorMessage", errorMessage);
////        return "ExceptionHandle";
////    }
//@RequestMapping("/error")
//public String handleError(HttpServletRequest request, Model model) {
//    Object statusAttribute = request.getAttribute("javax.servlet.error.status_code");
//
//    if (statusAttribute == null) {
//        // Nếu không có status, bạn có thể xử lý theo cách bạn muốn hoặc trả về lỗi mặc định
//        return "Error"; // Hoặc một trang lỗi mặc định
//    }
//
//    int statusCode;
//    try {
//        statusCode = (int) statusAttribute; // Chuyển đổi giá trị của statusAttribute sang kiểu int
//    } catch (ClassCastException e) {
//        // Xử lý lỗi chuyển đổi kiểu
//        return "Error"; // Hoặc một trang lỗi mặc định
//    }
//
//    HttpStatus httpStatus = HttpStatus.resolve(statusCode);
//
//    if (httpStatus == HttpStatus.NOT_FOUND) {
//        String errorMessage = "Oops, no such page found!";
//        ApiException apiException = new ApiException(
//                errorMessage,
//                HttpStatus.NOT_FOUND,
//                ZonedDateTime.now(ZoneId.of("Z"))
//        );
//
//        model.addAttribute("errorMessage", errorMessage);
//           model.addAttribute("timestamp", apiException.getTimesptamp());
//        model.addAttribute("status", apiException.getHttpStatus().value());
//        return "ExceptionHandle"; // Trang lỗi của bạn
//    }
//
//    // Xử lý các mã lỗi khác hoặc trả về trang lỗi mặc định
//    return "Error"; // Hoặc một trang lỗi mặc định
//}
//
//}
