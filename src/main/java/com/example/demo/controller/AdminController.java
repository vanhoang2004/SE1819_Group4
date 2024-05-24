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
    StudentRepository str;
    ClassRepository cr;
    SubjectRepository sjr;

    @Autowired
    public AdminController(UserRepository ur, AdminRepository ar, ManagerRepository mr, TeacherRepository tr, StudentRepository str, ClassRepository cr, SubjectRepository sjr) {
        this.ur = ur;
        this.ar = ar;
        this.mr = mr;
        this.tr = tr;
        this.str = str;
        this.cr = cr;
        this.sjr = sjr;
    }

    public List<User> getUsers(){
        return ur.findAll();
    }

    public List<Subject> getSubjects(){
        return sjr.findAll();
    }

    public List<Class> getClasses(){
        return cr.findAll();
    }
    //Users
    @GetMapping("/home")
    public String getUsers(Model model){
        model.addAttribute("users",getUsers());
        return "home";
    }

//Admins
    @GetMapping("/admins")
    public String getAdmins(Model model){
        model.addAttribute("users",getUsers());
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
        model.addAttribute("users",getUsers());
        model.addAttribute("subjects",getSubjects());
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
        model.addAttribute("users",getUsers());
        model.addAttribute("subjects",getSubjects());
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
        model.addAttribute("users",getUsers());
        model.addAttribute("classes",getClasses());
        List<Student> students = str.findAll();
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
        model.addAttribute("classes",getClasses());
        return "classes";
    }
    @GetMapping("/edit-class")
    public String editClass() {
        return "edit-class";
    }
}
