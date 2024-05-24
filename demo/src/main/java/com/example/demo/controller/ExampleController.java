package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.MockTest;
import com.example.demo.entity.Subject;
import com.example.demo.repository.ChapterRepository;
import com.example.demo.repository.MockTestRepository;
import com.example.demo.service.IChapter;
import com.example.demo.service.IMockTestService;
import com.example.demo.service.ISubjectService;
import com.example.demo.service.SubjectService;



@Controller

public class ExampleController {
	
	private final ISubjectService iSubjectService;
    private final IMockTestService iMockTestService;
    private final IChapter iChapter;
    

    @Autowired
    public ExampleController(ISubjectService iSubjectService, IMockTestService iMockTestService, IChapter iChapter) {
		
		this.iSubjectService = iSubjectService;
		this.iMockTestService = iMockTestService;
		this.iChapter = iChapter;
	}
	
@GetMapping("/students")
    public String listSubject(Model theModel) {
	List<Subject> subjects= iSubjectService.getAllSubject();
	theModel.addAttribute("subjects", subjects);
    return "home";
}
	
	@GetMapping("/teachers")
public String list1() {
    return "edit";
}
    @GetMapping("/listpage")
    public String list2(Model theModel) {
    	List<MockTest> mocktest=iMockTestService.getAllMockTest();
    	theModel.addAttribute("mocktest", mocktest);
        return "list";
    }
    @GetMapping("/mocktests/{subjectId}")
    public String listMockTestsBySubject(@PathVariable int subjectId, Model theModel) {
        List<MockTest> mockTests = iMockTestService.findMockTestBySubjectId(subjectId);
        theModel.addAttribute("mocktests", mockTests);
        return "list";
    }
//    @GetMapping("/chapters/{subjectId}")
//    public String listChaptersBySubject(@PathVariable int subjectId, Model theModel) {
//        List<MockTest> mockTests = iMockTestService.findMockTestBySubjectId(subjectId);
//        theModel.addAttribute("mocktests", mockTests);
//        return "list";
//    }
}
