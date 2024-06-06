package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import com.example.demo.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping ("/teacher")
public class TeacherController {

    SubjectRepository su;
    ClassRepository clas;
    MocktestRepository mt;
    TeacherRepository tr;
    StudentRepository sr;
    MaterialRepository mr;
    TeacherMaterialsRepository tmr;
    TeacherPracticeRepository tpr;
    QuestionRepository qr;

    @Autowired
    public TeacherController(SubjectRepository su, ClassRepository clas, MocktestRepository mt,
                             StudentRepository sr,MaterialRepository mr, TeacherRepository tr,
                             TeacherMaterialsRepository tmr, TeacherPracticeRepository tpr,
                             QuestionRepository qr) {
        this.su = su;
        this.clas = clas;
        this.mt = mt;
        this.sr = sr;
        this.mr = mr;
        this.tr = tr;
        this.tmr = tmr;
        this.tpr = tpr;
        this.qr = qr;
    }

//Delete
    @GetMapping("/home")
    public String getUsers(Model model){
        List<Subject> subjects = su.findAll();
        System.out.println(subjects.isEmpty() + "****************************************************************");
        model.addAttribute("subjects",subjects);
        return "home";
    }

// get class list in homepage
    @GetMapping("/homepage")
    public String listClasses(Model model, @Param("keyword") String keyword) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
        Teacher teacher = tr.findByUserid(name);
        List<Class> classes = clas.findClassByUserId(name);

        if(keyword!=null){
            classes=clas.searchClass(teacher.getUserId(),keyword);
        }
        model.addAttribute("classes", classes);
        model.addAttribute("teacher", teacher);
        return "homepage";
    }

    //get mocktest according to subjectid from username, and classcode from click
    @GetMapping("/classpage/{classcode}")
    public String getMocktest(Model model, @PathVariable int classcode, @Param("keyword") String keyword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        //get subjectid from username from authentication
        int subjectid = tr.findSubjectidByUserName(name);
        //take list mocktest by subjectid
        List<Mocktest> mocktests = mt.findMocktestBySubjectid(subjectid);
        if(keyword!=null){
            mocktests=mt.searchMocktest(subjectid, keyword);
        }
        model.addAttribute("classcode", classcode);
        model.addAttribute("mocktests", mocktests);
        return "classpage";
    }

    //get school material list according to subjectid from username
    @GetMapping("/materiallist/{classcode}")
    public String getMaterial(Model model, @PathVariable int classcode, @Param("keyword") String keyword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        //get subjectid from username from authentication
        int subjectid = tr.findSubjectidByUserName(name);
        List<Material> materials = mr.findMaterialBySubjectid(subjectid);
        if(keyword!=null){
            materials=mr.searchMaterial(subjectid, keyword);
        }
        model.addAttribute("materials", materials);
        return "materiallist";
    }

    //get studentlist
    @GetMapping("/studentlist/{classcode}")
    public String getStudent(Model model, @PathVariable int classcode, @Param("keyword") String keyword) {
        List<Student> students = sr.findStudentByClasscode(classcode);
        if(keyword!=null){
            students=sr.searchStudentByUsername(classcode, keyword);
        }
        model.addAttribute("students", students);
        return "studentlist";
    }

    //view class material according to subjectid from username, and classcode from click
    @GetMapping("/teachermateriallist/{classcode}")
    public String getTeacherMaterials(Model model, @PathVariable int classcode, @Param("keyword") String keyword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        int subjectid = tr.findSubjectidByUserName(name);
        List<TeacherMaterials> teacherMaterials = tmr.findTeacherMaterialsByClassAndSubject(classcode, subjectid);
        if(keyword!=null){
            teacherMaterials=tmr.searchTeacherMaterial(subjectid, classcode, keyword);
        }
        model.addAttribute("teachermaterials", teacherMaterials);
        return "teachermateriallist";
    }

    //get class practice according to subjectid from username, and classcode from click
    @GetMapping("/teacherpracticelist/{classcode}")
    public String getTeacherPractice(Model model, @PathVariable int classcode, @Param("keyword") String keyword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        int subjectid = tr.findSubjectidByUserName(name);
        List<TeacherPractice> teacherpractices = tpr.findTeacherPracticeByClassAndSubject(classcode, subjectid) ;
        if(keyword!=null){
            teacherpractices=tpr.searchTeacherPractice(subjectid, classcode, keyword);
        }
        model.addAttribute("teacherpractices", teacherpractices);
        return "teacherpracticelist";
    }



    //CLASS MATERIALS
    //edit class materials
    @GetMapping("/teachermaterial/editpage/{id}")
    public String list1(Model model,@PathVariable Integer id) {
        TeacherMaterials teacherMaterials = tmr.findTeacherMaterialsById(id);
        model.addAttribute("mate", teacherMaterials);
        return "edit-teachermaterial";
    }
    @PostMapping("/editpage")
    public String postTeacherMaterial(@ModelAttribute("mate") TeacherMaterials teacherMaterials) {
        tmr.save(teacherMaterials);
        return "teachermateriallist";
    }

    //delete class materials
    @GetMapping("/teachermaterial/{id}")
    public String deleteTeacherMaterial(@ModelAttribute TeacherMaterials teacherMaterials,@PathVariable int id) {
        tmr.deleteById(id);
        return "teachermateriallist";
    }

    // create class materials
    @PostMapping("/teachermaterial")
    public String getTeacherPractice(@ModelAttribute TeacherMaterials teacherMaterials){
        tmr.save(teacherMaterials);
        return "teachermateriallist";
    }

    // CLASS PRATICE
    //edit class practice (no edit question)
    @GetMapping("/teacherpractice/editpage1/{id}")
    public String list2(Model model,@PathVariable Integer id) {
        TeacherPractice teacherpractices = tpr.findTeacherPracticeById(id);
        model.addAttribute("prac", teacherpractices);
        return "edit-teacherpractice";
    }
    @PostMapping("/editpage1")
    public String postTeacherPractice(@ModelAttribute("prac") TeacherPractice teacherpractices) {
        tpr.save(teacherpractices);
        return "teachermateriallist";
    }

    //delete class practice(no delete questions)
    @GetMapping("/teacherpractice/{id}")
    public String deleteTeacherPractice(@ModelAttribute TeacherPractice teacherpractices,@PathVariable int id) {
        tpr.deleteById(id);
        return "teacherpracticelist";
    }

    //create new class practice (no adding questions)
    @PostMapping("/teacherpractice")
    public String getPractice(@ModelAttribute TeacherPractice teacherpractices){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedNow = now.format(formatter);
        teacherpractices.setPublishdate(formattedNow);
        tpr.save(teacherpractices);
        return "teachermateriallist";
    }


    //EDIT QUESTION
    //Show all question in a class practice
    @GetMapping("/practicequestion/{teacherpracticeid}")
    public String list3(Model model, @PathVariable Integer teacherpracticeid) {
        List<Question> questions = qr.findQuestionByTeacherPracticeid(teacherpracticeid);

        model.addAttribute("questions", questions);
        return "practicequestionlist";
    }
    //Edit question in class practice
    @GetMapping("/practicequestionedit/editpage2/{questionid}")
    public String list4(Model model,@PathVariable Integer questionid) {
        Question question = qr.findQuestionByQuestionId(questionid);
        model.addAttribute("question", question);
        return "edit-practicequestion";
    }
    @PostMapping("/editpage2")
    public String postQuestionPracticeEdit(@ModelAttribute("question") Question questions) {
        qr.save(questions);
        return "practicequestionlist";
    }

    //delete Question in class practice(no delete questions)
    @GetMapping("/practicequestiondelete/{questionid}")
    public String deletePracticeQuestion(@ModelAttribute Question questions,@PathVariable Integer questionid) {
        qr.deleteById(questionid);
        return "teacherpracticelist";
    }

    //Add a new question
    @PostMapping("/praticequestionedit")
    public String addQuestion(@ModelAttribute Question questions){
        qr.save(questions);
        qr.insertQuestionByQuestionId(3);
        return "teacherpracticelist";
    }


    //get what
//    @GetMapping("/classpage/{classcode}")
//    public String getClasscode(Model model, @PathVariable int classcode) {
//        model.addAttribute("class", classcode);
//        return "classpage";
//    }

    //don't know what
    @GetMapping("/list")
    public String getList() {
        return "list";
    }

}
