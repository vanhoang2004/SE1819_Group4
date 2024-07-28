package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class OtpUtil {

    private String value;
    private LocalDateTime creationTime;

    public void newOtp(){
        Random r = new Random();
        int ran = r.nextInt(100000,999999);
        String output = Integer.toString(ran);
        creationTime = LocalDateTime.now();
        value = output;
    }

    public String getString() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
