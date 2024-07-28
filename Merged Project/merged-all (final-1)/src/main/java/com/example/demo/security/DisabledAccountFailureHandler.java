package com.example.demo.security;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DisabledAccountFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
//        System.out.println("Using custom failure handling.");
        // Check if the failure is due to a disabled account
//        System.out.println("Is it because account is disabled? " + (exception instanceof DisabledException));
//        System.out.println("Exception name: " + exception.getClass());
        if (exception instanceof DisabledException) {
            response.sendRedirect("/login?disabled");
        } else {
            response.sendRedirect("/login?error");
        }
    }
}
