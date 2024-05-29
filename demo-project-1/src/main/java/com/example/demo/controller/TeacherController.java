package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import com.example.demo.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping ("/teacher")
public class TeacherController {

    SubjectRepository su;
    ClassRepository clas;
    MocktestRepository mt;
    TeacherRepository tr;
    StudentRepository sr;
    MaterialRepository mr;

    @Autowired
    public TeacherController(SubjectRepository su, ClassRepository clas, MocktestRepository mt, StudentRepository sr,MaterialRepository mr, TeacherRepository tr) {
        this.su = su;
        this.clas = clas;
        this.mt = mt;
        this.sr = sr;
        this.mr = mr;
        this.tr = tr;
    }



//Users
    @GetMapping("/home")
    public String getUsers(Model model){
        List<Subject> subjects = su.findAll();
        System.out.println(subjects.isEmpty() + "****************************************************************");
        model.addAttribute("subjects",subjects);
        return "home";
    }


    @GetMapping("/studentlist/{classcode}")
    public String getStudent(Model model, @PathVariable int classcode) {
        List<Student> students = sr.findStudentByClasscode(classcode);
        for(Student x:students) System.out.println(x);
        model.addAttribute("students", students);
        return "studentlist";
    }
//    @GetMapping("/mocktests/{subjectId}")
//    public String listMockTestsBySubject(@PathVariable int subjectId, Model theModel) {
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    String username = auth.getName();

    @GetMapping("/homepage")
    public String listClasses(Model model) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();

        Teacher teacher = tr.findByUserid(name);
        List<Class> classes = clas.findClassByUserId(name);


        model.addAttribute("classes", classes);
        model.addAttribute("teacher", teacher);

        return "homepage"; // Assuming your view template is named `list.html`
    }

    @GetMapping("/materiallist/{subjectid}")
    public String getMaterial(Model model, @PathVariable int subjectid) {
        List<Material> materials = mr.findMaterialBySubjectid(subjectid);
        model.addAttribute("materials", materials);
        return "materiallist";
    }

    @GetMapping("/classpage/{subjectid}")
    public String getMocktest(Model model, @PathVariable int subjectid) {
        List<Mocktest> mocktests = mt.findMocktestBySubjectid(subjectid);
        model.addAttribute("mocktests", mocktests);
        return "classpage";
    }

    @GetMapping("/list")
    public String getList() {
        return "list";
    }

    @GetMapping("/classes/{classcode}&&{subjectid}")
    public String getClassContent(Model model, @PathVariable int classcode, @PathVariable int subjectid) {
        List<Mocktest> mocktests = mt.findMocktestBySubjectid(subjectid);
        model.addAttribute("mocktests", mocktests);
        return "classpage";
    }
//
//    @GetMapping("/classes/{classcode}")
//    public String listMockTestByClasscode(PathVariable int classcode, Model model, Principal principal) {
//        Teacher teacher = TeacherRepository.findUserID(principal.getUserid);
//        List<MockTest> mockTests = iMockTestService.findMockTestBySubjectId(subjectId);
//        theModel.addAttribute("mocktests", mockTests);
//        return "list";
//    }


//    @GetMapping("/edit-admin")
//    public String editAdmin() {
//        return "edit-admin";
//    }

////Managers
//    @GetMapping("/managers")
//    public String managers() {
//    return "managers";
//}
//    @GetMapping("/edit-manager")
//    public String editManager() {
//        return "edit-manager";
//    }
//
////Teachers
//    @GetMapping("/teachers")
//    public String teachers() {
//    return "teachers";
//}
//    @GetMapping("/edit-teacher")
//    public String editTeacher() {
//        return "edit-teacher";
//    }
//
////Students
//    @GetMapping("/students")
//    public String students() {
//    return "students";
//}
//    @GetMapping("/edit-student")
//    public String editStudent() {
//        return "edit-student";
//    }
}
