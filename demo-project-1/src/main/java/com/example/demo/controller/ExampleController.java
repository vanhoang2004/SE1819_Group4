package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping ("/test")
public class ExampleController {
    @Autowired
    QuestionRepository question;
    @Autowired
    SubjectRepository subject;
    @Autowired
     MockTestRepository mock;
    @Autowired
    MaterialRepository material;
    @Autowired
    ChapterRepository chapter;
    @Autowired
    LevelRepository level;
    @Autowired
    ManagerRepository manager;
    @Autowired
    MockQuestionRepository mockquestion;
    @GetMapping("/homepage")
    public String listH(Model model,@ModelAttribute Manager manager) {
        Subject subjects = subject.findSubjectByUserID(11);
        model.addAttribute("subject",subjects);
        //ghep code thi suaw
        //List<MockTest> mockTests = mock.findMockTestByUserId(id);
     List<MockTest> mockTests = mock.findMockTestByUserId(11);

        model.addAttribute("mock", mockTests);
        return "home";
    }
    @GetMapping("/questionbank")
    public String questionbank(Model model) {
       Subject subjects = subject.findSubjectByUserID(11);
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        List<Question> questions = question.findQuestionBySubjectID(11);
        model.addAttribute("ques", questions);
        return "questionbank";
    }
    @PostMapping("/questionbank")
    public String getQuestion(@ModelAttribute Question questions){
        question.save(questions);
        return "redirect:/test/questionbank";
    }
    @GetMapping("questionbank/editpage/{id}")
    public String list1(Model model,@PathVariable int id) {
      Question questions = question.findQuestionBYID(id);
        model.addAttribute("ques", questions);
        Subject subjects = subject.findSubjectByUserID(11);
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        return "edit-question";
    }
    @PostMapping("/editpage")
    public String postQuestion(@ModelAttribute("ques") Question questions) {
        question.save(questions);
        return "redirect:/test/questionbank";
    }

    @GetMapping("/questionbank/{id}")
    public String deleteQuestion(@ModelAttribute Question questions,@PathVariable int id) {
        question.deleteById(id);
        return "redirect:/test/questionbank";
    }

    @GetMapping("/mockdetails/{id}")
    public String mockQuestion(Model model,@PathVariable int id) {
        Subject subjects = subject.findSubjectByUserID(11);
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        List<Question> questions = question.mockTestDetails(id);
        model.addAttribute("ques", questions);
        return "mockquestion";
    }

    @GetMapping("/materials")
    public String materrialList(Model model) {
        Subject subjects = subject.findSubjectByUserID(11);
        model.addAttribute("subject",subjects);
     List<Chapters> chapters = chapter.findChapterBySubject(subjects.getId());
        model.addAttribute("chapter",chapters);
        List<Materials> mater = material.findMaterialsbySubject(11);
        model.addAttribute("material",mater);
        return "Materiallist";

    }
    @PostMapping("/materials")
    public String getMaterials(@ModelAttribute Materials materials){
        material.save(materials);
        return "redirect:/test/materials";
    }
   // @Autowired


    @GetMapping("/mocktests")
    public String getMockTests(Model model,@ModelAttribute Manager manager ) {
        Subject subjects = subject.findSubjectByUserID(11);
        model.addAttribute("subject",subjects);

        // ghep code thi sua
        //List<MockTest> mockTests = mock.findMockTestByUserId(manager.getSubjectid());
        List<MockTest> mockTests = mock.findMockTestByUserId(11);

        model.addAttribute("mock", mockTests);

        return "list";
    }
     @PostMapping("/mocktests")
    public String postMockTests(@ModelAttribute MockTest mockTest) {
        mock.save(mockTest);
        return "redirect:/test/mocktests";
    }

//    @GetMapping("/mocktest/{id}")
//    public String getMockTestById(@PathVariable int id, Model model){
//        model.addAttribute("mock-single",mock.findById(id));
//        return "mockquestion";
//    }
}
