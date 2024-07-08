package com.example.demo.security;

import com.example.demo.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository ur; // Assume you have a UserRepository to find user by username

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            String userDetails = (String) auth.getPrincipal();
            System.out.println("Logged user: " + ur.findUserByUsername(userDetails));
            request.getSession().setAttribute("logged_user", ur.findUserByUsername(userDetails));
        }

        filterChain.doFilter(request, response);
    }
}
