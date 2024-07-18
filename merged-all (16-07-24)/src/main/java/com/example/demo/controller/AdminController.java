package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import com.example.demo.entity.Class;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.util.ExcelHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

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
//        System.out.println("Test session attribute: " + (List<Class>) session.getAttribute("current_classes"));
        if(search != null) m.addAttribute("users",ur.search(search));
        else m.addAttribute("users",ur.findAll());
        currentClasses = null;
        remainingClasses = null;
        return "admin/home";
    }

    @GetMapping("/home/enable")
    public String enableUserFromHome(@RequestParam("userId") int duid){
        if(ur.findUserById(duid)==null) throw new ApiRequestException("User not found.");
        enableById(duid);
        return "redirect:/admin/home?enabled";
    }

    @GetMapping("/users/enable")
    public String enableUser(@RequestParam("userId") int duid){
        User u = ur.findUserById(duid);
        if(u==null) throw new ApiRequestException("User not found.");
        enableById(duid);
        return switch (u.getRole()) {
            case "Admin" -> "redirect:/admin/admins?disabled";
            case "Manager" -> "redirect:/admin/managers?disabled";
            case "Teacher" -> "redirect:/admin/teachers?disabled";
            case "Student" -> "redirect:/admin/students?disabled";
            default -> "redirect:/admin/home?disabled";
        };
    }

    @GetMapping("/home/disable")
    public String disableUserFromHome(@RequestParam("userId") int duid, HttpServletRequest request){
        if(ur.findUserById(duid)==null) throw new ApiRequestException("User not found.");
        disableById(duid);
        if(duid == ((User)request.getSession().getAttribute("logged_user")).getUserId()) return "auto-logout";
        return "admin/home";
    }

    @GetMapping("/users/disable")
    public String disableUser(@RequestParam("userId") int duid, HttpServletRequest request){
        User u = ur.findUserById(duid);
        if(u==null) throw new ApiRequestException("User not found.");
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
        if(u==null) throw new ApiRequestException("User not found.");
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
    public String saveUserAdmin(@ModelAttribute("user") Admin a, @RequestParam(value="oldRole", required=false) String oldRole, RedirectAttributes ra){
        if(oldRole!=null && !Arrays.asList("Admin","Manager","Teacher","Student").contains(oldRole)) throw new ApiRequestException("Invalid user role.");
        try {
        if(a.getUser().getRole() == null || a.getUser().getRole().isEmpty()) a.getUser().setRole("Admin");
        if(a.getUser().getUserId() == null) a.getUser().setEnabled(true);
        a.setUserId((a.getUser().getUserId()));
//        System.out.println("New admin info: " + a);
            ar.saveAndFlush(a);
        }catch(DataIntegrityViolationException e){
            System.out.println("Erroneous admin caught: " + a);
            ra.addFlashAttribute("admin",a);
            return "redirect:/admin/admins?uniqueconstraint";
        }
        if(oldRole!=null && !oldRole.isEmpty()) deleteOldRole(oldRole,a.getUserId());
        return "redirect:/admin/admins?saved";
    }

    @PostMapping("/users/save/manager")
    public String saveUserManager(@ModelAttribute("user") Manager ma, @RequestParam(value="oldRole", required=false) String oldRole, RedirectAttributes ra){
        if(oldRole!=null && !Arrays.asList("Admin","Manager","Teacher","Student").contains(oldRole)) throw new ApiRequestException("Invalid user role.");
        try {
        if(ma.getUser().getRole() == null || ma.getUser().getRole().isEmpty()) ma.getUser().setRole("Manager");
        if(ma.getUser().getUserId() == null) ma.getUser().setEnabled(true);
        ma.setUserId((ma.getUser().getUserId()));
        mr.saveAndFlush(ma);
        }catch(DataIntegrityViolationException e){
            ra.addFlashAttribute("manager",ma);
            return "redirect:/admin/managers?uniqueconstraint";
        }
        if(oldRole!=null && !oldRole.isEmpty()) deleteOldRole(oldRole,ma.getUserId());
        return "redirect:/admin/managers?saved";
    }

    @PostMapping("/users/save/teacher")
    public String saveUserTeacher(@ModelAttribute("user") Teacher t, @RequestParam(value="oldRole", required=false) String oldRole, RedirectAttributes ra){
        if(oldRole!=null && !Arrays.asList("Admin","Manager","Teacher","Student").contains(oldRole)) throw new ApiRequestException("Invalid user role.");
        try{
        if(t.getUser().getRole() == null || t.getUser().getRole().isEmpty()) t.getUser().setRole("Teacher");
        if(t.getUser().getUserId() == null) t.getUser().setEnabled(true);
        t.setUserId((t.getUser().getUserId()));
        t.setSubject(sjr.findSubjectById(t.getSubject().getSubjectId()));
        t.setClasses(currentClasses);
        tr.saveAndFlush(t);
        }catch(DataIntegrityViolationException e){
            ra.addFlashAttribute("teacher",t);
            return "redirect:/admin/teachers?uniqueconstraint";
        }
        if(oldRole!=null && !oldRole.isEmpty()) deleteOldRole(oldRole,t.getUserId());
        return "redirect:/admin/teachers?saved";
    }

    @PostMapping("/users/save/student")
    public String saveUserStudent(@ModelAttribute("user") Student s, @RequestParam(value="oldRole", required=false) String oldRole, RedirectAttributes ra){
        if(oldRole!=null && !Arrays.asList("Admin","Manager","Teacher","Student").contains(oldRole)) throw new ApiRequestException("Invalid user role.");
        try{
        if(s.getUser().getRole() == null || s.getUser().getRole().isEmpty()) s.getUser().setRole("Student");
        if(s.getUser().getUserId() == null) s.getUser().setEnabled(true);
        s.setUserId((s.getUser().getUserId()));
        s.setSclass(cr.findClassById(s.getSclass().getClassCode()));
            System.out.println("Student before saving: " + s);
        str.saveAndFlush(s);
        }catch(DataIntegrityViolationException e){
            ra.addFlashAttribute("student",s);
            return "redirect:/admin/students?uniqueconstraint";
        }
        if(oldRole!=null && !oldRole.isEmpty()) deleteOldRole(oldRole,s.getUserId());
        return "redirect:/admin/students?saved";
    }

//Admins
    @GetMapping("/admins")
    public String getAdmins(Model m, @RequestParam(value="search",required = false) String search, RedirectAttributes ra){
        if(search!=null) m.addAttribute("admins", ar.search(search));
        else m.addAttribute("admins",ar.findAll());
//        System.out.println("Is ${admin} empty? " + m.getAttribute("admin"));
        if(m.getAttribute("admin") == null) m.addAttribute("admin", new Admin());
        return "admin/list-admins";
    }

    @GetMapping("/import")
    public String importAdmins(){
        return "admin/file-import";
    }

    @PostMapping("/import")
    public String process(@RequestParam("file") MultipartFile file, Model m) throws Exception {
        if(!ExcelHelper.isExcelFile(file)){
            System.out.println("Not an Excel file?");
            throw new ApiRequestException("Invalid file type or corrupted file.");
        }
        String fileName = uploadExcelFile(file);
        if (fileName != null) {
            System.out.println("Uploaded file name: " + fileName);

            // Use the absolute path specified
            String excelPath = "D:\\uni materials\\su24\\swp391\\excels\\" + fileName;
            System.out.println("Excel file path: " + excelPath);

            ExcelHelper excelHelper = new ExcelHelper(excelPath);

            List<Admin> foundAdmins = new ArrayList<>();
            List<Map<String, String>> data = excelHelper.readData();

            System.out.println("Data from Excel file:");
            for (Map<String, String> row : data) {
                Admin a = Admin.fromMap(row);
                foundAdmins.add(a);
                User u = ur.save(a.getUser());
                a.setUserId(u.getUserId());
                ar.save(a);
//                System.out.println(a);
            }

            //delete all imported files after insert
            deleteAllFilesInDirectory("D:\\uni materials\\su24\\swp391\\excels\\");
        }
        return "admin/confirm-import";
    }

//Managers
    @GetMapping("/managers")
    public String getManagers(Model m, @RequestParam(value="search",required = false) String search){
        if(search!=null) m.addAttribute("managers", mr.search(search));
        else m.addAttribute("managers",mr.findAll());
        if(m.getAttribute("manager") == null) m.addAttribute("manager", new Manager());
        else m.addAttribute("manager", m.getAttribute("manager"));
        m.addAttribute("subjects",sjr.findAll());
        return "admin/list-managers";
    }

//Teachers
    @GetMapping("/teachers")
    public String getTeachers(Model m, @RequestParam(value="search",required = false) String search, @RequestParam(value="filter",required = false) Integer filter) {
        if(search!=null) m.addAttribute("teachers", tr.search(search));
        else if(filter!=null) m.addAttribute("teachers", tr.filterBySubject(filter));
        else m.addAttribute("teachers", tr.findAll());
        if(m.getAttribute("teacher") == null) m.addAttribute("teacher", new Teacher());
        else m.addAttribute("teacher",new Teacher());
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
        if(m.getAttribute("student") == null) m.addAttribute("student", new Student());
        else m.addAttribute("student", new Student());
        m.addAttribute("classes", cr.findAll());
        return "admin/list-students";
    }

//Classes
    @GetMapping("/classes")
    public String getClasses(Model m, @RequestParam(value="search",required = false) String search){
        if(search!=null) m.addAttribute("classes",cr.search(search));
        else m.addAttribute("classes",cr.findAll());
        Class c = (Class) m.getAttribute("class");
        if(c == null) m.addAttribute("class", new Class());
        else m.addAttribute("class",c);
        return "admin/list-classes";
    }

    @GetMapping("/classes/update")
    public String updateClass(@RequestParam("classCode") int uccode, Model m) {
        Class c = cr.findClassById(uccode);
        if(c==null) throw new ApiRequestException("Class not found.");
        m.addAttribute("class",c);
        return "admin/update-class";
    }

    @PostMapping("/classes/save")
    public String saveClass(@ModelAttribute("class") Class c, RedirectAttributes ra){
        if(!c.getClassName().matches("^(12)[A-Za-z]+\\d*$")) return "redirect:/admin/classes?invalid";
        try{
            cr.save(c);
        }catch(DataIntegrityViolationException e){
            ra.addFlashAttribute("class",c);
            return "redirect:/admin/classes?uniqueconstraint";
        }
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
        if(t==null) throw new ApiRequestException("User not found.");
        Class c = cr.findClassById(dccode);
        if(c==null) throw new ApiRequestException("Class not found.");
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
        if(t==null) throw new ApiRequestException("User not found.");
        Class c = cr.findClassById(dccode);
        if(c==null) throw new ApiRequestException("Class not found.");

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
//        System.out.println("Old role: " + oldRole + " " + id);
        switch(oldRole){
            case "Admin": ar.deleteById(id); break;
            case "Manager": mr.deleteById(id); break;
            case "Teacher": tr.deleteById(id); break;
            case "Student": str.deleteById(id); break;
        }
    }

    private String uploadExcelFile(MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            // Use the absolute path specified
            String uploadDir = "D:\\uni materials\\su24\\swp391\\excels\\";
            Path path = Paths.get(uploadDir, multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            return multipartFile.getOriginalFilename();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void deleteAllFilesInDirectory(String directory) throws IOException {
        Path directoryPath = Paths.get(directory);
        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
            Files.walkFileTree(directoryPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }
}
