package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import com.example.demo.entity.Classes;
import com.example.demo.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    LevelRepository lr;
    ChapterRepository cr;
    TakenMocktestRepository tmtr;
    ChartService cs;

    @Autowired
    public TeacherController(SubjectRepository su, ClassRepository clas, MocktestRepository mt,
                             StudentRepository sr,MaterialRepository mr, TeacherRepository tr,
                             TeacherMaterialsRepository tmr, TeacherPracticeRepository tpr,
                             QuestionRepository qr, LevelRepository lr, ChapterRepository cr,
                             TakenMocktestRepository tmtr, ChartService cs) {
        this.su = su;
        this.clas = clas;
        this.mt = mt;
        this.sr = sr;
        this.mr = mr;
        this.tr = tr;
        this.tmr = tmr;
        this.tpr = tpr;
        this.qr = qr;
        this.lr = lr;
        this.cr = cr;
        this.tmtr = tmtr;
        this.cs = cs;
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
        List<Classes> classes = clas.findClassByUserId(name);
        int subjectid = tr.findSubjectidByUserName(name);

        if(keyword!=null){
            classes=clas.searchClass(teacher.getUserId(),keyword);
        }
        model.addAttribute("classes", classes);
        model.addAttribute("subjectid", subjectid);
        model.addAttribute("teacher", teacher);
        return "homepage";
    }

    //get question bank
    @GetMapping("/questionbank/{subjectid}")
    public String getQuestionbank(Model model, @PathVariable int subjectid, @Param("keyword") String keyword, @RequestParam(value="filter",required = false) String filter) {

        //List<Question> questions = qr.findQuestionBySubjectId(subjectid);

        List<Question> questions;

        if (keyword != null && !keyword.isEmpty()) {
            // Search mocktest by keyword
            questions = qr.searchQuestionBytitle(subjectid, keyword);
        } else if (filter != null && !filter.isEmpty()) {
            // Filter mocktest by filter
            questions = qr.findQuestionByChapterId(subjectid, Integer.parseInt(filter));
        } else {
            // Find mocktest by subjectid
            questions = qr.findQuestionBySubjectId(subjectid);
        }

        List<Level> levels = lr.findAll();
        List<Chapter> chapters = cr.findChapterBySubject(subjectid);

        model.addAttribute("questions", questions);
        model.addAttribute("levels", levels);
        model.addAttribute("chapters", chapters);
        model.addAttribute("subjectid", subjectid);


        return "teacherquestionbank";
    }

    //get mocktest according to subjectid from username, and classcode from click
    @GetMapping("/classpage/{classcode}")
    public String getMocktest(Model model, @PathVariable int classcode, @Param("keyword") String keyword, @RequestParam(value="filter",required = false) String filter) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        //get subjectid from username from authentication
        int subjectid = tr.findSubjectidByUserName(name);
        //take list mocktest by subjectid
        List<Mocktest> mocktests;

        if (keyword != null && !keyword.isEmpty()) {
            // Search mocktest by keyword
            mocktests = mt.searchMocktest(subjectid, keyword);
        } else if (filter != null && !filter.isEmpty()) {
            // Filter mocktest by filter
            mocktests = mt.filterMocktest(subjectid, filter);
        } else {
            // Find mocktest by subjectid
            mocktests = mt.findMocktestBySubjectid(subjectid);
        }

        model.addAttribute("classcode", classcode);
        model.addAttribute("mocktests", mocktests);
        return "classpage";
    }

    //get score table
    @GetMapping("/mockscore/{classcode}/{mocktestid}")
    public String getMockScore(Model model, @PathVariable int classcode, @PathVariable int mocktestid) throws IOException {
        List<TakenMockTest> studentScores = tmtr.listScore(classcode, mocktestid);
        int cnt1 = 0, cnt2 = 0, cnt3 = 0;
        for(TakenMockTest i : studentScores) {
            if(i.getScore() <= 6) {
                cnt1++;
            } else if (i.getScore() > 6 && i.getScore() <= 8) {
                cnt2++;
            }
            else if(i.getScore() > 8 && i.getScore() <= 10){
                cnt3++;
            }
        }
        String pieChartBase64 = cs.generatePieChart(cnt1, cnt2, cnt3);
        model.addAttribute("pieChartBase64", pieChartBase64);
        model.addAttribute("classcode", classcode);
        model.addAttribute("mocktestid", mocktestid);
        model.addAttribute("studentscores", studentScores);
        return "studentmocktestscore"; // Ensure this view name matches your actual HTML template
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
        int classcode = teacherMaterials.getClasses().getClasscode();
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
        teacherpractices.setPublishdate(now);
        tpr.save(teacherpractices);
        model.addAttribute("classcode", classcode);
        return "redirect:/teacher/teacherpracticelist/" + classcode;
    }


    //EDIT QUESTION
    //Show all question in a class practice
    @GetMapping("/practicequestion/{teacherpracticeid}/{classcode}")
    public String list3(Model model, @PathVariable Integer teacherpracticeid, @PathVariable Integer classcode) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        int subjectid = tr.findSubjectidByUserName(name);
        List<Level> levels = lr.findAll();
        List<Chapter> chapters = cr.findChapterBySubject(subjectid);
        List<Question> questions = qr.findQuestionByTeacherPracticeid(teacherpracticeid);
        
        model.addAttribute("subjectid", subjectid);
        model.addAttribute("levels", levels);
        model.addAttribute("chapters", chapters);
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
    public String addQuestion(Model model, @ModelAttribute Question nques, @PathVariable Integer teacherpracticeid, @PathVariable Integer classcode){
        qr.save(nques);
        qr.insertQuestionByQuestionId(teacherpracticeid, nques.getQuestionid());
        return "redirect:/teacher/practicequestion/" + teacherpracticeid + "/" + classcode;
    }


    //Send question for approval
    @PostMapping("questionapprove/{subjectid}")
    public String sendQuestion(Model model, @ModelAttribute Question nques, @PathVariable Integer subjectid){
        qr.save(nques);
        return "redirect:/teacher/questionbank/" + subjectid;
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
