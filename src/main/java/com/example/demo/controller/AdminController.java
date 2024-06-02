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

    @GetMapping("/home/enable")
    public String enableUserFromHome(@RequestParam("userId") int duid){
        enableById(duid);
        return "redirect:/admin/home?enabled";
    }

    @GetMapping("/users/enable")
    public String enableUser(@RequestParam("userId") int duid){
        User u = ur.findUserById(duid);
        enableById(duid);
        switch(u.getRole()){
            case "Admin":
                return "redirect:/admin/admins?enabled";
            case "Manager":
                return "redirect:/admin/managers?enabled";
            case "Teacher":
                return "redirect:/admin/teachers?enabled";
            case "Student":
                return "redirect:/admin/students?enabled";
            default: return "redirect:/admin/home?enabled";
        }
    }

    @GetMapping("/home/disable")
    public String disableUserFromHome(@RequestParam("userId") int duid){
        disableById(duid);
        return "redirect:/admin/home?disabled";
    }

    @GetMapping("/users/disable")
    public String disableUser(@RequestParam("userId") int duid){
        User u = ur.findUserById(duid);
        disableById(duid);
        switch(u.getRole()){
            case "Admin":
                return "redirect:/admin/admins?disabled";
            case "Manager":
                return "redirect:/admin/managers?disabled";
            case "Teacher":
                return "redirect:/admin/teachers?disabled";
            case "Student":
                return "redirect:/admin/students?disabled";
            default: return "redirect:/admin/home?disabled";
        }
    }

    //Admins
    @GetMapping("/admins")
    public String getAdmins(Model m){
        m.addAttribute("admins",ar.findAll());
        m.addAttribute("admin", new Admin());
        return "list-admins";
    }

    @GetMapping("/admins/update")
    public String updateAdmin(@RequestParam("userId") int ucid, Model m) {
        Admin a = ar.findAdminById(ucid);
        m.addAttribute("admin",a);
        return "update-admin";
    }

    @PostMapping("/admins/save")
    public String saveAdmin(@ModelAttribute("admin") Admin a){
        if(a.getUser().getRole() == null || a.getUser().getRole().isEmpty()) a.getUser().setRole("Admin");
        if(a.getUser().getUserId() == null) a.getUser().setEnabled(true);
        System.out.println(a);
        User temp = ur.save(a.getUser());
        a.setUserId(temp.getUserId());
        System.out.println(a);
        ar.save(a);
        return "redirect:/admin/admins?saved";
    }

    @GetMapping("/admins/disable")
    public String disableAdmin(@RequestParam("userId") int daid){
        disableById(daid);
        return "redirect:/admin/admins?disabled";
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
        return "list-managers";
    }

    @GetMapping("/managers/update")
    public String updateManager(@RequestParam("userId") int ucid, Model m) {
        m.addAttribute("manager",mr.findManagerById(ucid));
        m.addAttribute("subjects",sjr.findAll());
        return "update-manager";
    }

    @PostMapping("/managers/save")
    public String saveManager(@ModelAttribute("manager") Manager ma){
        if(ma.getUser().getRole() == null || ma.getUser().getRole().isEmpty()) ma.getUser().setRole("Manager");
        if(ma.getUser().getUserId() == null) ma.getUser().setEnabled(true);
        User temp = ur.save(ma.getUser());
        ma.setUserId(temp.getUserId());
        ma.setSubject(sjr.findSubjectById(ma.getSubjectId()));
        System.out.println(ma);
        mr.save(ma);
        return "redirect:/admin/managers?saved";
    }

    @GetMapping("/managers/disable")
    public String disableManager(@RequestParam("userId") int dmid){
        disableById(dmid);
        return "redirect:/admin/managers?disabled";
    }

//Teachers
    @GetMapping("/teachers")
    public String getTeachers(Model m) {
        m.addAttribute("teachers", tr.findAll());
        m.addAttribute("teacher",new Teacher());
        m.addAttribute("subjects",sjr.findAll());
        return "list-teachers";
    }

    @GetMapping("/teachers/update")
    public String updateTeacher(@RequestParam("userId") int ucid, Model m) {
        m.addAttribute("teacher",tr.findTeacherById(ucid));
        m.addAttribute("subjects",sjr.findAll());
        return "update-teacher";
    }

    @PostMapping("/teachers/save")
    public String saveTeacher(@ModelAttribute("teacher") Teacher t){
        if(t.getUser().getRole() == null || t.getUser().getRole().isEmpty()) t.getUser().setRole("Teacher");
        if(t.getUser().getUserId() == null) t.getUser().setEnabled(true);
        User temp = ur.save(t.getUser());
        t.setUserId(temp.getUserId());
        t.setSubject(sjr.findSubjectById(t.getSubjectId()));
        System.out.println(t);
        tr.save(t);
        return "redirect:/admin/teachers?saved";
    }

    @GetMapping("/teachers/disable")
    public String disableTeacher(@RequestParam("userId") int dtid){
        disableById(dtid);
        return "redirect:/admin/teachers?disabled";
    }

//Students
    @GetMapping("/students")
    public String getStudents(Model m){
        m.addAttribute("students",str.findAll());
        m.addAttribute("student", new Student());
        m.addAttribute("classes", cr.findAll());
        return "list-students";
    }

    @GetMapping("/students/update")
    public String updateStudent(@RequestParam("userId") int ucid, Model m) {
        m.addAttribute("student",str.findStudentById(ucid));
        m.addAttribute("classes",cr.findAll());
        return "update-student";
    }

    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute("student") Student s){
        if(s.getUser().getRole() == null || s.getUser().getRole().isEmpty()) s.getUser().setRole("Student");
        if(s.getUser().getUserId() == null) s.getUser().setEnabled(true);
        User temp = ur.save(s.getUser());
        s.setUserId(temp.getUserId());
        s.setSclass(cr.findClassById(s.getClassCode()));
        System.out.println(s);
        str.save(s);
        return "redirect:/admin/students?saved";
    }

    @GetMapping("/students/disable")
    public String disableStudent(@RequestParam("userId") int dsid){
        disableById(dsid);
        return "redirect:/admin/students?disabled";
    }

//Classes
    @GetMapping("/classes")
    public String getClasses(Model m){
        m.addAttribute("class",new Class());
        m.addAttribute("classes",cr.findAll());
        return "classes";
    }

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

    @GetMapping("classes/disable")
    public String deleteClass(@RequestParam("classCode") int dccode){
        cr.deleteById(dccode);
        return "redirect:/admin/classes?deleted";
    }

//Helper functions
    private void disableById(int duid) {
        User u = ur.findUserById(duid);
        u.setEnabled(false);
        ur.save(u);
    }

    private void enableById(int duid) {
        User u = ur.findUserById(duid);
        u.setEnabled(true);
        ur.save(u);
    }
}
