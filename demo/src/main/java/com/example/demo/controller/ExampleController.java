package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.MockTest;
import com.example.demo.entity.Subject;
import com.example.demo.repository.MockTestRepository;
import com.example.demo.service.IMockTestService;
import com.example.demo.service.ISubjectService;
import com.example.demo.service.SubjectService;



@Controller
@RequestMapping ("/test")
public class ExampleController {
	private ISubjectService iSubjectService;
	private IMockTestService iMockTestService;

	
public ExampleController(ISubjectService iSubjectService) {
		this.iSubjectService = iSubjectService;
	}
	
@GetMapping("/homepage")
    public String listSubject(Model theModel) {
	List<Subject> subjects= iSubjectService.getAllSubject();
	theModel.addAttribute("subjects", subjects);
    return "home";
}
	@Autowired
    public ExampleController(IMockTestService iMockTestService) {

	this.iMockTestService = iMockTestService;
}
	@GetMapping("/editpage")
public String list1() {
    return "edit";
}
    @GetMapping("/listpage")
    public String list2(Model theModel) {
    	List<MockTest> mocktest=iMockTestService.getAllMockTest();
    	theModel.addAttribute("mocktest", mocktest);
        return "list";
    }
}
