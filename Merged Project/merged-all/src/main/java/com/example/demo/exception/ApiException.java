package com.example.demo.exception;
import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

public class ApiException {
    private final String message;
    //private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timesptamp;

    public ApiException(String message,
                        //Throwable throwable,
                        HttpStatus httpStatus,
                        ZonedDateTime timesptamp) {
        this.message = message;
        //this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timesptamp = timesptamp;
    }

    public String getMessage() {
        return message;
    }

//    public Throwable getThrowable() {
//        return throwable;
//    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimesptamp() {
        return timesptamp;
    }
}
