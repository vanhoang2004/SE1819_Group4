package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import com.example.demo.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//Users
    @GetMapping("/home")
    public String getUsers(Model m){
        m.addAttribute("users",ur.findAll());
        return "home";
    }

//Admins
    @GetMapping("/admins")
    public String getAdmins(Model m){
        m.addAttribute("admins",ar.findAll());
        m.addAttribute("admin", new Admin());
        return "admins";
    }

    @GetMapping("/admins/update")
    public String updateAdmin(@RequestParam("userId") int ucid, Model m) {
        Admin a = ar.findAdminById(ucid);
        m.addAttribute("admin",a);
        return "adminedit";
    }

    @PostMapping("/admins/save")
    public String saveAdmin(@ModelAttribute("admin") Admin a){
        if(a.getUser().getRole() == null || a.getUser().getRole().isEmpty()) a.getUser().setRole("ROLE_ADMIN");
        if(a.getUser().getUserId()==0) a.getUser().setEnabled(true);
        User temp = ur.save(a.getUser());
        a.setUserId(temp.getUserId());
//        System.out.println(a);
        ar.save(a);
        return "redirect:/admin/admins?saved";
    }

    @GetMapping("/admins/delete")
    public String deleteAdmin(@RequestParam("userId") int dcid){
        ar.deleteById(dcid);
        return "redirect:/admin/admins?deleted";
    }

//Managers
    @GetMapping("/managers")
    public String getManagers(Model m){
//        List managers = mr.findAll();
//        m.addAttribute("managers",managers);
        m.addAttribute("managers",mr.findAll());
//        System.out.println(managers);

        m.addAttribute("manager",new Manager());

//        List subjects = sjr.findAll();
//        m.addAttribute("subjects",subjects);
        m.addAttribute("subjects",sjr.findAll());
//        System.out.println(subjects);
        return "managers";
    }

    @GetMapping("/managers/update")
    public String updateManager(@RequestParam("userId") int ucid, Model m) {
        m.addAttribute("manager",mr.findManagerById(ucid));
        m.addAttribute("subjects",sjr.findAll());
        return "manageredit";
    }

    @PostMapping("/managers/save")
    public String saveManager(@ModelAttribute("manager") Manager ma){
        if(ma.getUser().getRole() == null || ma.getUser().getRole().isEmpty()) ma.getUser().setRole("ROLE_MANAGER");
        if(ma.getUser().getUserId()==0) ma.getUser().setEnabled(true);
        User temp = ur.save(ma.getUser());
        ma.setUserId(temp.getUserId());
        ma.setSubject(sjr.findSubjectById(ma.getSubjectId()));
        System.out.println(ma);
        mr.save(ma);
        return "redirect:/admin/managers?saved";
    }

    @GetMapping("/managers/delete")
    public String deleteManager(@RequestParam("userId") int dcid){
        mr.deleteById(dcid);
        return "redirect:/admin/managers?deleted";
    }

    //Teachers
    @GetMapping("/teachers")
    public String getTeachers(Model m) {
        m.addAttribute("teachers", tr.findAll());
        m.addAttribute("teacher",new Teacher());
        m.addAttribute("subjects",sjr.findAll());
        return "teachers";
    }

    @GetMapping("/teachers/update")
    public String updateTeacher(@RequestParam("userId") int ucid, Model m) {
        m.addAttribute("teacher",tr.findTeacherById(ucid));
        m.addAttribute("subjects",sjr.findAll());
        return "teacheredit";
    }

    @PostMapping("/teachers/save")
    public String saveTeacher(@ModelAttribute("teacher") Teacher t){
        if(t.getUser().getRole() == null || t.getUser().getRole().isEmpty()) t.getUser().setRole("ROLE_TEACHER");
        if(t.getUser().getUserId()==0) t.getUser().setEnabled(true);
        User temp = ur.save(t.getUser());
        t.setUserId(temp.getUserId());
        t.setSubject(sjr.findSubjectById(t.getSubjectId()));
        System.out.println(t);
        tr.save(t);
        return "redirect:/admin/teachers?saved";
    }

    @GetMapping("/teachers/delete")
    public String deleteTeacher(@RequestParam("userId") int dcid){
        mr.deleteById(dcid);
        return "redirect:/admin/teachers?deleted";
    }

//Students
    @GetMapping("/students")
    public String getStudents(Model m){
        m.addAttribute("students",str.findAll());
        m.addAttribute("student", new Student());
        m.addAttribute("classes", cr.findAll());
        return "students";
    }

    @GetMapping("/students/update")
    public String updateStudent(@RequestParam("userId") int ucid, Model m) {
        m.addAttribute("student",str.findStudentById(ucid));
        m.addAttribute("classes",cr.findAll());
        return "studentedit";
    }

    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute("student") Student s){
        if(s.getUser().getRole() == null || s.getUser().getRole().isEmpty()) s.getUser().setRole("ROLE_TEACHER");
        if(s.getUser().getUserId()==0) s.getUser().setEnabled(true);
        User temp = ur.save(s.getUser());
        s.setUserId(temp.getUserId());
        s.setSclass(cr.findClassById(s.getClassCode()));
        System.out.println(s);
        str.save(s);
        return "redirect:/admin/students?saved";
    }

    @GetMapping("/students/delete")
    public String deleteStudent(@RequestParam("userId") int dcid){
        str.deleteById(dcid);
        return "redirect:/admin/students?deleted";
    }

//Classes
    @GetMapping("/classes")
    public String getClasses(Model m){
        m.addAttribute("class",new Class());
        m.addAttribute("classes",cr.findAll());
        return "classes";
    }
//
//    @GetMapping("/classes/add")
//    public String addForm(Model m){
//        Class c = new Class();
//        m.addAttribute("class",c);
//        return "classedit";
//    }

    @GetMapping("/classes/update")
    public String updateClass(@RequestParam("classCode") int uccode, Model m) {
        Class c = cr.findClassById(uccode);
        m.addAttribute("class",c);
        return "classedit";
    }

    @PostMapping("/classes/save")
    public String saveClass(@ModelAttribute("class") Class c){
        cr.save(c);
        return "redirect:/admin/classes?saved";
    }

    @GetMapping("classes/delete")
    public String deleteClass(@RequestParam("classCode") int dccode){
        cr.deleteById(dccode);
        return "redirect:/admin/classes?deleted";
    }

//Helper functions
//    private String encryptNone(String s){
//        return "{noop}" + s;
//    }
}
