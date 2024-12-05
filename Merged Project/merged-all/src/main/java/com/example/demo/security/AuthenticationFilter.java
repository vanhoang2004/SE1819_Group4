package com.example.demo.security;

import com.example.demo.data.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String userDetails = (String) auth.getPrincipal();
            System.out.println("Username: " + userDetails);
            User u = ur.findUserByUsername(userDetails);
            HttpSession session = request.getSession();
            if (session.getAttribute("logged_user") == null) {
//            System.out.println("Logged user: " + ur.findUserByUsername(userDetails));
                session.setAttribute("logged_user", u);
            }
            System.out.println("Is user enabled? " + u.getEnabled());
            if(!u.getEnabled()) request.getRequestDispatcher("/logout").forward(request,response);

            User logged = (User) session.getAttribute("logged_user");
            boolean changed = !(u.getUsername().equals(logged.getUsername()) && u.getPassword().equals(logged.getPassword()));
            System.out.println("Did login info change? " + changed);
            if(changed) request.getRequestDispatcher("/logout").forward(request,response);
        }

        filterChain.doFilter(request, response);
    }
}
