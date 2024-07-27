package com.example.demo.security;

import com.example.demo.data.*;
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
    private UserRepository ur;

    @Autowired
    private AdminRepository ar;

    @Autowired
    private ManagerRepository mr;

    @Autowired
    private TeacherRepository tr;

    @Autowired
    private StudentRepository sr;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = request.getSession();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            System.out.println("Username: " + username);
            User u = ur.findUserByUsername(username);
            if (session.getAttribute("logged_user") == null) {
//            System.out.println("Logged user: " + ur.findUserByUsername(username));
                session.setAttribute("logged_user", u);
            }
            System.out.println("Is user enabled? " + u.getEnabled());
            if(!u.getEnabled()) request.getRequestDispatcher("/logout").forward(request,response);

            User logged = (User) session.getAttribute("logged_user");
            boolean changed = !(u.getUsername().equals(logged.getUsername()) && u.getPassword().equals(logged.getPassword()));
            System.out.println("Did login info change? " + changed);
            if(changed) request.getRequestDispatcher("/logout").forward(request,response);

            String role = u.getRole();
            switch (role){
                case "Admin":
                    if(session.getAttribute("logged_admin")==null) session.setAttribute("logged_admin",ar.findAdminById(u.getUserId()));
                    break;
                case "Manager":
                    if(session.getAttribute("logged_manager")==null) session.setAttribute("logged_manager",mr.findManagerById(u.getUserId()));
                    break;
                case "Teacher":
                    if(session.getAttribute("logged_teacher")==null) session.setAttribute("logged_teacher",tr.findTeacherById(u.getUserId()));
                case "Student":
                    if(session.getAttribute("logged_student")==null) session.setAttribute("logged_student",sr.findStudentById(u.getUserId()));
                    break;
            }
        }
        else{
//            session.setAttribute("logged_user",null);
//            session.setAttribute("logged_admin",null);
//            session.setAttribute("logged_manager",null);
//            session.setAttribute("logged_teacher",null);
//            session.setAttribute("logged_student",null);

            session.removeAttribute("logged_user");
            session.removeAttribute("logged_admin");
            session.removeAttribute("logged_manager");
            session.removeAttribute("logged_teacher");
            session.removeAttribute("logged_student");
        }
        filterChain.doFilter(request, response);
    }
}
