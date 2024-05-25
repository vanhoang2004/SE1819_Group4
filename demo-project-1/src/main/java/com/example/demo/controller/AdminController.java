package com.example.demo.controller;

import com.example.demo.data.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping ("/admin")
public class AdminController {

    UserRepository ur;

    @Autowired
    public AdminController(UserRepository ur) {
        this.ur = ur;
    }

//Users
    @GetMapping("/home")
    public String getUsers(Model model){
        List<User> users = ur.findAll();
        System.out.println(users.isEmpty() + "****************************************************************");
        model.addAttribute("users",users);
        return "home";
    }

//Admins
    @GetMapping("/admins")
    public String admins() {
    return "admins";
}
    @GetMapping("/edit-admin")
    public String editAdmin() {
        return "edit-admin";
    }

//Managers
    @GetMapping("/managers")
    public String managers() {
    return "managers";
}
    @GetMapping("/edit-manager")
    public String editManager() {
        return "edit-manager";
    }

//Teachers
    @GetMapping("/teachers")
    public String teachers() {
    return "teachers";
}
    @GetMapping("/edit-teacher")
    public String editTeacher() {
        return "edit-teacher";
    }

//Students
    @GetMapping("/students")
    public String students() {
    return "students";
}
    @GetMapping("/edit-student")
    public String editStudent() {
        return "edit-student";
    }
}
