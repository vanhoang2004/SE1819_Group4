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
        model.addAttribute("subjectid", subjectid);
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
        model.addAttribute("subjectid", subjectid);
        return "teacherpracticelist";
    }



    //CLASS MATERIALS
    //edit class materials
    @GetMapping("/teachermaterial/editpage/{id}/{classcode}")
    public String list1(Model model,@PathVariable Integer id, @PathVariable Integer classcode) {
        TeacherMaterials teacherMaterials = tmr.findTeacherMaterialsById(id);
        model.addAttribute("mate", teacherMaterials);
        model.addAttribute("classcode", classcode);
        return "edit-teachermaterial";
    }
    @PostMapping("/editpage/{classcode}")
    public String postTeacherMaterial(@ModelAttribute("mate") TeacherMaterials teacherMaterials, @PathVariable Integer classcode) {
        tmr.save(teacherMaterials);
        return "redirect:/teacher/teachermateriallist/" + classcode;
    }

    //delete class materials
    @GetMapping("/teachermaterial/{id}/{classcode}")
    public String deleteTeacherMaterial(Model model, @ModelAttribute TeacherMaterials teacherMaterials,@PathVariable int id, @PathVariable Integer classcode) {
        tmr.deleteById(id);
        model.addAttribute("classcode", classcode);
        return "redirect:/teacher/teachermateriallist/" + classcode;
    }

    // create class materials
    @PostMapping("/teachermaterial")
    public String getTeacherPractice(Model model, @ModelAttribute TeacherMaterials teacherMaterials){
        tmr.save(teacherMaterials);
        int classcode = teacherMaterials.getClasscode();
        return "redirect:/teacher/teachermateriallist/" + classcode;
    }

    // CLASS PRATICE
    //edit class practice (no edit question)
    @GetMapping("/teacherpractice/editpage1/{id}/{classcode}")
    public String list2(Model model,@PathVariable Integer id, @PathVariable Integer classcode) {
        TeacherPractice teacherpractices = tpr.findTeacherPracticeById(id);
        model.addAttribute("prac", teacherpractices);
        model.addAttribute("classcode", classcode);
        return "edit-teacherpractice";
    }
    @PostMapping("/editpage1/{classcode}")
    public String postTeacherPractice(Model model, @ModelAttribute("prac") TeacherPractice teacherpractices, @PathVariable Integer classcode) {
        tpr.save(teacherpractices);
        model.addAttribute("classcode", classcode);
        return "redirect:/teacher/teacherpracticelist/" + classcode;
    }

    //delete class practice(no delete questions)
    @GetMapping("/teacherpractice/{id}/{classcode}")
    public String deleteTeacherPractice(Model model, @ModelAttribute TeacherPractice teacherpractices,@PathVariable int id, @PathVariable Integer classcode) {
        tpr.deleteById(id);
        model.addAttribute("classcode", classcode);
        return "redirect:/teacher/teacherpracticelist/" + classcode;
    }

    //create new class practice (no adding questions)
    @PostMapping("/teacherpractice/{classcode}")
    public String getPractice(Model model,@ModelAttribute TeacherPractice teacherpractices, @PathVariable Integer classcode){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedNow = now.format(formatter);
        teacherpractices.setPublishdate(formattedNow);
        tpr.save(teacherpractices);
        model.addAttribute("classcode", classcode);
        return "redirect:/teacher/teacherpracticelist/" + classcode;
    }


    //EDIT QUESTION
    //Show all question in a class practice
    @GetMapping("/practicequestion/{teacherpracticeid}/{classcode}")
    public String list3(Model model, @PathVariable Integer teacherpracticeid, @PathVariable Integer classcode) {
        List<Question> questions = qr.findQuestionByTeacherPracticeid(teacherpracticeid);
        model.addAttribute("teacherpracticeid", teacherpracticeid);
        model.addAttribute("classcode", classcode);
        model.addAttribute("questions", questions);
        return "practicequestionlist";
    }
    //Edit question in class practice
    @GetMapping("/practicequestionedit/editpage2/{questionid}/{teacherpracticeid}/{classcode}")
    public String list4(Model model, @PathVariable Integer questionid, @PathVariable Integer teacherpracticeid, @PathVariable Integer classcode) {
        Question question = qr.findQuestionByQuestionId(questionid);
        model.addAttribute("teacherpracticeid", teacherpracticeid);
        model.addAttribute("classcode", classcode);
        model.addAttribute("question", question);
        return "edit-practicequestion";
    }
    @PostMapping("/editpage2/{teacherpracticeid}/{classcode}")
    public String postQuestionPracticeEdit(Model model, @ModelAttribute("question") Question questions, @PathVariable Integer teacherpracticeid, @PathVariable Integer classcode) {
        model.addAttribute("teacherpracticeid", teacherpracticeid);
        model.addAttribute("classcode", classcode);
        qr.save(questions);
        return "redirect:/teacher/practicequestion/" + teacherpracticeid + "/" + classcode;
    }

    //delete Question in class practice(no delete questions)
    @GetMapping("/practicequestiondelete/{questionid}/{teacherpracticeid}/{classcode}")
    public String deletePracticeQuestion(Model model, @ModelAttribute Question questions,@PathVariable Integer questionid, @PathVariable Integer teacherpracticeid, @PathVariable Integer classcode) {
        model.addAttribute("teacherpracticeid", teacherpracticeid);
        model.addAttribute("classcode", classcode);
        qr.DeleteQuestionByQuestionId(questionid);
        qr.deleteById(questionid);
        return "redirect:/teacher/practicequestion/" + teacherpracticeid + "/" + classcode;
    }

    //Add a new question
    @PostMapping("/praticequestionedit/{teacherpracticeid}/{classcode}")
    public String addQuestion(Model model, @ModelAttribute Question questions, @PathVariable Integer teacherpracticeid, @PathVariable Integer classcode){

        qr.save(questions);
        qr.insertQuestionByQuestionId(3);
        return "redirect:/teacher/practicequestion/" + teacherpracticeid + "/" + classcode;
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
