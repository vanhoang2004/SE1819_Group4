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
    @GetMapping("/homepage")
    public String listH(Model model) {
        List<Subject> subjects = subject.findAll();
        model.addAttribute("subject",subjects);
        List<MockTest> mockTests = mock.findAll();
        model.addAttribute("mock", mockTests);
        return "home";
    }
    @GetMapping("/questionbank")
    public String questionbank(Model model) {
        List<Subject> subjects = subject.findAll();
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findAll();
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        List<Question> questions = question.findAll();
        model.addAttribute("ques", questions);
        return "questionbank";
    }
    @GetMapping("/editpage")
    public String list1() {
        return "edit";
    }




    @GetMapping("/materials")
    public String materrialList(Model model) {
        List<Subject> subjects = subject.findAll();
        model.addAttribute("subject",subjects);
     List<Chapters> chapters = chapter.findAll();
        model.addAttribute("chapter",chapters);
        List<Materials> mater = material.findAll();
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
    public String getMockTests(Model model) {
        List<Subject> subjects = subject.findAll();
        model.addAttribute("subject",subjects);
        List<MockTest> mockTests = mock.findAll();
        model.addAttribute("mock", mockTests);

        return "list";
    }
     @PostMapping("/mocktests")
    public String postMockTests(@ModelAttribute MockTest mockTest) {
        mock.save(mockTest);
        return "redirect:/test/mocktests";
    }

    @GetMapping("/mocktest/{id}")
    public String getMockTestById(@PathVariable int id, Model model){
        model.addAttribute("mock-single",mock.findById(id));
        return "mockquestion";
    }
}
