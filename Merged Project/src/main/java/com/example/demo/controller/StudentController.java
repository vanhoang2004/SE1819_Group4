package com.example.demo.controller;

import com.example.demo.data.NotificationRepository;
import com.example.demo.data.StudentRepository;
import com.example.demo.entity.Notification;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    NotificationRepository nr;
    StudentRepository sr;

    @Autowired
    public StudentController(NotificationRepository nr, StudentRepository sr) {
        this.sr = sr;
        this.nr = nr;
    }

    @GetMapping("/home")
    public String home(HttpSession session){
        User u = (User) session.getAttribute("logged_user");
        Student s = sr.findStudentById(u.getUserId());
        Integer classCode = s.getSclass().getClassCode();
        session.setAttribute("all_notifs",nr.findAllByClassCode(classCode));
        session.setAttribute("gen_notifs",nr.findAllGeneral());
        session.setAttribute("class_notifs",nr.findByClassCode(classCode));
        return "student/home";
    }

}
