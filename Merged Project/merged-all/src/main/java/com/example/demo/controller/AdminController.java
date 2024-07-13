package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import com.example.demo.entity.Class;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;
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

    List<Class> currentClasses;
    List<Class> remainingClasses;

//Users
    @GetMapping("/home")
    public String getUsers(Model m, @RequestParam(value="search",required = false) String search, HttpSession session){
        System.out.println("Test session attribute: " + (List<Class>) session.getAttribute("current_classes"));
        if(search != null) m.addAttribute("users",ur.search(search));
        else m.addAttribute("users",ur.findAll());
        currentClasses = null;
        remainingClasses = null;
        return "admin/home";
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
    public String disableUserFromHome(@RequestParam("userId") int duid, HttpServletRequest request){
//        User u = ur.findUserById(duid);
        disableById(duid);
        if(duid == ((User)request.getSession().getAttribute("logged_user")).getUserId()) return "auto-logout";
        return "admin/home";
    }

    @GetMapping("/users/disable")
    public String disableUser(@RequestParam("userId") int duid, HttpServletRequest request){
        User u = ur.findUserById(duid);
        disableById(duid);
        if(duid == ((User)request.getSession().getAttribute("logged_user")).getUserId()) return "auto-logout";
        return switch (u.getRole()) {
            case "Admin" -> "redirect:/admin/admins?disabled";
            case "Manager" -> "redirect:/admin/managers?disabled";
            case "Teacher" -> "redirect:/admin/teachers?disabled";
            case "Student" -> "redirect:/admin/students?disabled";
            default -> "redirect:/admin/home?disabled";
        };
    }

    @PostMapping("/users/changerole/admin")
    public String changeRoleFromAdmin(@ModelAttribute("user") Admin a, RedirectAttributes ra){
        a.setUserId(a.getUser().getUserId());
        int userId = a.getUserId();
        ra.addAttribute("old_role","Admin");
        ra.addAttribute("role", a.getUser().getRole());
        return "redirect:/admin/users/update?userId=" + userId;
    }

    @PostMapping("/users/changerole/manager")
    public String changeRoleFromManager(@ModelAttribute("user") Manager ma, RedirectAttributes ra){
        ma.setUserId(ma.getUser().getUserId());
        int userId = ma.getUserId();
        ra.addAttribute("old_role","Manager");
        ra.addAttribute("role", ma.getUser().getRole());
        return "redirect:/admin/users/update?userId=" + userId;
    }

    @PostMapping("/users/changerole/teacher")
    public String changeRoleFromTeacher(@ModelAttribute("user") Teacher t, RedirectAttributes ra){
        t.setUserId(t.getUser().getUserId());
        int userId = t.getUserId();
        ra.addAttribute("old_role","Teacher");
        ra.addAttribute("role", t.getUser().getRole());
        return "redirect:/admin/users/update?userId=" + userId;
    }

    @PostMapping("/users/changerole/student")
    public String changeRoleFromStudent(@ModelAttribute("user") Student s, RedirectAttributes ra){
        s.setUserId(s.getUser().getUserId());
        int userId = s.getUserId();
        ra.addAttribute("old_role","Student");
        ra.addAttribute("role", s.getUser().getRole());
        return "redirect:/admin/users/update?userId=" + userId;
    }

    @GetMapping("/users/update")
    public String updateUser(@RequestParam("userId") int uuid, @ModelAttribute("role") String role, @ModelAttribute("old_role") String oldRole, Model m, RedirectAttributes ra, HttpSession session) {
        User u = ur.findUserById(uuid);
        if(role==null || role.isEmpty()) role = u.getRole();
        ra.addAttribute("old_role",oldRole);
        switch (role){
            case "Admin":
                Admin a = ar.findAdminById(uuid);
                if(a==null){
                    a = new Admin();
                    a.setUserId(u.getUserId());
                    u.setRole("Admin");
                    a.setUser(u);
                }
                m.addAttribute("user",a);
                return "admin/update-user-admin";
            case "Manager":
                Manager ma = mr.findManagerById(uuid);
                if(ma==null){
                    ma = new Manager();
                    ma.setUserId(u.getUserId());
                    u.setRole("Manager");
                    ma.setUser(u);
                }
                m.addAttribute("user",ma);
                m.addAttribute("subjects",sjr.findAll());
                return "admin/update-user-manager";
            case "Teacher":
                Teacher t = tr.findTeacherById(uuid);
                if(t==null){
                    t = new Teacher();
                    t.setUserId(u.getUserId());
                    u.setRole("Teacher");
                    t.setUser(u);
                }
                m.addAttribute("user",t);
                m.addAttribute("subjects",sjr.findAll());
//                m.addAttribute("classes",cr.findAll());
                if(currentClasses == null) currentClasses = t.getClasses();
                if(remainingClasses == null) remainingClasses = cr.findAll();
                if(currentClasses != null && !currentClasses.isEmpty()) {
                    Iterator<Class> iterator = remainingClasses.iterator();
                    while (iterator.hasNext()) {
                        Class c = iterator.next();
                        if (currentClasses.contains(c)) iterator.remove();  // Safe removal using Iterator
                    }
                }
                m.addAttribute("current_classes",currentClasses);
                m.addAttribute("remaining_classes",remainingClasses);
                return "admin/update-user-teacher";
            case "Student":
                Student s = str.findStudentById(uuid);
                if(s==null){
                    s = new Student();
                    s.setUserId(u.getUserId());
                    u.setRole("Student");
                    s.setUser(u);
                }
                m.addAttribute("user",s);
                m.addAttribute("classes",cr.findAll());
                return "admin/update-user-student";
            default: return "admin/home";
        }
    }

    @PostMapping("/users/save/admin")
    public String saveUserAdmin(@ModelAttribute("user") Admin a, @RequestParam(value="oldRole", required=false) String oldRole){
        if(a.getUser().getRole() == null || a.getUser().getRole().isEmpty()) a.getUser().setRole("Admin");
        if(a.getUser().getUserId() == null) a.getUser().setEnabled(true);
//        a.setUserId((a.getUser().getUserId()));
//        System.out.println("New admin info: " + a);
        ar.saveAndFlush(a);
        if(oldRole!=null && !oldRole.isEmpty()) deleteOldRole(oldRole,a.getUserId());
        return "redirect:/admin/admins?saved";
    }

    @PostMapping("/users/save/manager")
    public String saveUserManager(@ModelAttribute("user") Manager ma, @RequestParam(value="oldRole", required=false) String oldRole){
        if(ma.getUser().getRole() == null || ma.getUser().getRole().isEmpty()) ma.getUser().setRole("Admin");
        if(ma.getUser().getUserId() == null) ma.getUser().setEnabled(true);
//        ma.setUserId((ma.getUser().getUserId()));
        mr.saveAndFlush(ma);
        if(oldRole!=null && !oldRole.isEmpty()) deleteOldRole(oldRole,ma.getUserId());
        return "redirect:/admin/managers?saved";
    }

    @PostMapping("/users/save/teacher")
    public String saveUserTeacher(@ModelAttribute("user") Teacher t, @RequestParam(value="oldRole", required=false) String oldRole){
        if(t.getUser().getRole() == null || t.getUser().getRole().isEmpty()) t.getUser().setRole("Admin");
        if(t.getUser().getUserId() == null) t.getUser().setEnabled(true);
        t.setUserId((t.getUser().getUserId()));
        t.setClasses(currentClasses);
        tr.saveAndFlush(t);
        if(oldRole!=null && !oldRole.isEmpty()) deleteOldRole(oldRole,t.getUserId());
        return "redirect:/admin/teachers?saved";
    }

    @PostMapping("/users/save/student")
    public String saveUserStudent(@ModelAttribute("user") Student s, @RequestParam(value="oldRole", required=false) String oldRole){
        if(s.getUser().getRole() == null || s.getUser().getRole().isEmpty()) s.getUser().setRole("Admin");
        if(s.getUser().getUserId() == null) s.getUser().setEnabled(true);
//        s.setUserId((s.getUser().getUserId()));
        str.saveAndFlush(s);
        if(oldRole!=null && !oldRole.isEmpty()) deleteOldRole(oldRole,s.getUserId());
        return "redirect:/admin/students?saved";
    }

//Admins
    @GetMapping("/admins")
    public String getAdmins(Model m){
        m.addAttribute("admins",ar.findAll());
        m.addAttribute("admin", new Admin());
        return "admin/list-admins";
    }

//Managers
    @GetMapping("/managers")
    public String getManagers(Model m, @RequestParam(value="search",required = false) String search){
        if(search!=null) m.addAttribute("managers", mr.search(search));
        else m.addAttribute("managers",mr.findAll());
        m.addAttribute("manager",new Manager());
        m.addAttribute("subjects",sjr.findAll());
        return "admin/list-managers";
    }

//Teachers
    @GetMapping("/teachers")
    public String getTeachers(Model m, @RequestParam(value="search",required = false) String search, @RequestParam(value="filter",required = false) Integer filter) {
        if(search!=null) m.addAttribute("teachers", tr.search(search));
        else if(filter!=null) m.addAttribute("teachers", tr.filterBySubject(filter));
        else m.addAttribute("teachers", tr.findAll());
        m.addAttribute("teacher",new Teacher());
        m.addAttribute("subjects",sjr.findAll());
        currentClasses = null;
        remainingClasses = null;
        return "admin/list-teachers";
    }

//Students
    @GetMapping("/students")
    public String getStudents(Model m, @RequestParam(value="search",required = false) String search, @RequestParam(value="filter",required = false) Integer filter){
        if(search!=null) m.addAttribute("students",str.search(search));
        else if(filter!=null) m.addAttribute("students",str.filterByClass(filter));
        else m.addAttribute("students",str.findAll());
        m.addAttribute("student", new Student());
        m.addAttribute("classes", cr.findAll());
        return "admin/list-students";
    }

//Classes
    @GetMapping("/classes")
    public String getClasses(Model m, @RequestParam(value="search",required = false) String search){
        if(search!=null) m.addAttribute("classes",cr.search(search));
        else m.addAttribute("classes",cr.findAll());
        m.addAttribute("class",new Class());
        return "admin/list-classes";
    }

    @GetMapping("/classes/update")
    public String updateClass(@RequestParam("classCode") int uccode, Model m) {
        Class c = cr.findClassById(uccode);
        m.addAttribute("class",c);
        return "admin/update-class";
    }

    @PostMapping("/classes/save")
    public String saveClass(@ModelAttribute("class") Class c){
        if(!c.getClassName().matches("^(12)[A-Za-z]+\\d*$")) return "redirect:/admin/classes?invalid";
        cr.save(c);
        return "redirect:/admin/classes?saved";
    }

    @GetMapping("classes/delete")
    public String deleteClass(@RequestParam("classCode") int dccode){
        try{
            cr.deleteById(dccode);
        } catch(Exception e){
            return "redirect:/admin/classes?constraint";
        }
        return "redirect:/admin/classes?deleted";
    }

//Teacher - Class
    @GetMapping("/teacher-class")
    public String getTeacherClass(Model m){
        m.addAttribute("teachers", tr.findAll());
        return "admin/list-teacher-class";
    }

    @GetMapping("/teacher-class/delete")
    public String deleteClassFromTeacher(@RequestParam("userId") int utid, @RequestParam("classCode") int dccode, RedirectAttributes ra){
        Teacher t = tr.findTeacherById(utid);
        Class c = cr.findClassById(dccode);
//        System.out.println("\nRemaining classes before adding: " + remainingClasses);
        remainingClasses.add(c);
//        System.out.println("Added class: " + c);
//        System.out.println("Remaining classes after adding: " + remainingClasses);

//        System.out.println("\nCurrent classes before removing: " + currentClasses);
        currentClasses.removeIf(obj -> obj.getClassCode() == c.getClassCode());
//        System.out.println("Current classes before removing: " + currentClasses);
//        System.out.println();

        ra.addAttribute("user",t);
        return "redirect:/admin/users/update?userId=" + utid;
    }

    @GetMapping("/teacher-class/add")
    public String addClassToTeacher(@RequestParam("userId") int utid, @RequestParam("classCode") int dccode, RedirectAttributes ra){
        Teacher t = tr.findTeacherById(utid);
        Class c = cr.findClassById(dccode);

//        System.out.println("\nCurrent classes before adding: " + currentClasses);
        currentClasses.add(c);
//        System.out.println("Added class: " + c);
//        System.out.println("Current classes after adding: " + currentClasses);

//        System.out.println("\nRemaining classes before removing: " + remainingClasses);
        remainingClasses.removeIf(obj -> obj.getClassCode() == c.getClassCode());
//        System.out.println("Remaining classes after removing: " + remainingClasses);
//        System.out.println();

        ra.addAttribute("user",t);
        return "redirect:/admin/users/update?userId=" + utid;
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

    private void deleteOldRole(String oldRole, int id){
        System.out.println("Old role: " + oldRole + " " + id);
        switch(oldRole){
            case "Admin": ar.deleteById(id); break;
            case "Manager": mr.deleteById(id); break;
            case "Teacher": tr.deleteById(id); break;
            case "Student": str.deleteById(id); break;
        }
    }
}
