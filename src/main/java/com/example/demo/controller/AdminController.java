package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import com.example.demo.entity.Class;
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
    AdminRepository ar;
    ManagerRepository mr;
    TeacherRepository tr;
    StudentRepository sr;
    ClassRepository cr;

    @Autowired
    public AdminController(UserRepository ur, AdminRepository ar, ManagerRepository mr, TeacherRepository tr, StudentRepository sr, ClassRepository cr) {
        this.ur = ur;
        this.ar = ar;
        this.mr = mr;
        this.tr = tr;
        this.sr = sr;
        this.cr = cr;
    }

    //Users
    @GetMapping("/home")
    public String getUsers(Model model){
        List<User> users = ur.findAll();
        model.addAttribute("users",users);
        return "home";
    }

//Admins
    @GetMapping("/admins")
    public String getAdmins(Model model){
        List<Admin> admins = ar.findAll();
        model.addAttribute("admins",admins);
        return "admins";
    }
    @GetMapping("/edit-admin")
    public String editAdmin() {
        return "edit-admin";
    }

//Managers
    @GetMapping("/managers")
    public String getManagers(Model model){
        List<Manager> managers = mr.findAll();
        model.addAttribute("managers",managers);
        return "managers";
    }
    public String managers() {
    return "managers";
}
    @GetMapping("/edit-manager")
    public String editManager() {
        return "edit-manager";
    }

//Teachers
    @GetMapping("/teachers")
    public String getTeachers(Model model){
        List<Teacher> teachers = tr.findAll();
        model.addAttribute("teachers",teachers);
        return "teachers";
    }
    @GetMapping("/edit-teacher")
    public String editTeacher() {
        return "edit-teacher";
    }

//Students
    @GetMapping("/students")
    public String getStudents(Model model){
        List<Student> students = sr.findAll();
        model.addAttribute("students",students);
        return "students";
    }
    @GetMapping("/edit-student")
    public String editStudent() {
        return "edit-student";
    }

//Classes
    @GetMapping("/classes")
    public String getClasses(Model model){
        List<Class> classes = cr.findAll();
        model.addAttribute("classes",classes);
        return "classes";
    }
    @GetMapping("/edit-class")
    public String editClass() {
        return "edit-class";
    }
}
