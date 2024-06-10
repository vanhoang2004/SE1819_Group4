package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.entity.Chapter;
import com.example.demo.entity.Material;
import com.example.demo.entity.MockTest;
import com.example.demo.entity.Question;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.entity.TeacherMaterial;
import com.example.demo.entity.TeacherPractice;
import com.example.demo.service.ChapterService;
import com.example.demo.service.MaterialService;
import com.example.demo.service.MockTestService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.StudentService;
import com.example.demo.service.SubjectService;
import com.example.demo.service.TeacherMaterialService;
import com.example.demo.service.TeacherPracticeService;



@Controller

public class ExampleController {
	
	private final SubjectService subjectService;
    private final MockTestService mockTestService;
    private final ChapterService chapterService;
    private final MaterialService materialService;
    private final StudentService studentService;
    private final TeacherMaterialService teacherMaterialService;
    private final TeacherPracticeService teacherPracticeService;
    private final QuestionService questionService;

   
	@Autowired
	public ExampleController(SubjectService subjectService, MockTestService mockTestService,
			ChapterService chapterService, MaterialService materialService, StudentService studentService,
			TeacherMaterialService teacherMaterialService, TeacherPracticeService teacherPracticeService,
			QuestionService questionService) {
		super();
		this.subjectService = subjectService;
		this.mockTestService = mockTestService;
		this.chapterService = chapterService;
		this.materialService = materialService;
		this.studentService = studentService;
		this.teacherMaterialService = teacherMaterialService;
		this.teacherPracticeService = teacherPracticeService;
		this.questionService = questionService;
	}
	
@GetMapping("/tester")
public String test() {
	return "test";
}



@GetMapping("/students")
    public String listSubject(Model theModel) {
	List<Subject> subjects= subjectService.getAllSubject();
	theModel.addAttribute("subjects", subjects);
    return "home";
}
	


//@GetMapping("/home")
//	public String home(@ModelAttribute("loggedUser") User u) {
//	
//	String role = u.getRole();
//	if(role=="Student") return "students";
//	else if(role=="Teacher") return "teachers";
//}





	//	@GetMapping("/teachers")
//public String list1() {
//    return "edit";
//}
//    @GetMapping("/listpage")
//    public String list2(Model theModel) {
//    	List<MockTest> mocktest=mockTestService.getAllMockTest();
//    	theModel.addAttribute("mocktest", mocktest);
//        return "list";
//    }


	@GetMapping("/mocktests/{subjectId}")
    public String listMockTestsBySubject(@PathVariable int subjectId, Model theModel) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Student student = studentService.getStudentByUsername(username);
        List<MockTest> mockTests = mockTestService.findMockTestBySubjectId(subjectId);
        theModel.addAttribute("mocktests", mockTests);
        List<Chapter> chapters= chapterService.findChapterBySubjectId(subjectId);
        theModel.addAttribute("chapters", chapters);
        theModel.addAttribute("subjectId", subjectId);
       Subject subject = subjectService.getSubjectById(subjectId);
        theModel.addAttribute("subject", subject);
        List<TeacherMaterial> teachermaterial = teacherMaterialService.getAllTeachermaterialByClassAndSubject(student.getClasscode(),subjectId);
        List<TeacherPractice> teacherpractice = teacherPracticeService.getTeacherPracticeByClassAndSubject(student.getClasscode(), subjectId);
        System.out.println("ahihi");
        for(int i =0; i<teacherpractice.size();i++) {
        	System.out.println(teacherpractice.get(i).getTitle());
        }
        theModel.addAttribute("teacherpractice",teacherpractice);
        theModel.addAttribute("classmaterial", teachermaterial);
        return "list";
    }
//    @GetMapping("/chapters/{subjectId}")
//    public String listChaptersBySubject(@PathVariable int subjectId, Model theModel) {
//        List<MockTest> mockTests = iMockTestService.findMockTestBySubjectId(subjectId);
//        theModel.addAttribute("mocktests", mockTests);
//        return "list";
//    }
    @GetMapping("/materials")
    public String viewMaterials(@RequestParam("chapterId") int chapterId, @RequestParam("subjectId") int subjectId, Model model) {
        List<Material> materials = materialService.getAllMaterialBySubjectAndChapter(chapterId, subjectId);
        Optional<Chapter> chapter=chapterService.getChapterByChapterId(chapterId);
        model.addAttribute("materials", materials);
        model.addAttribute("chapter", chapter);
        return "materials"; // 
    }
    
    @GetMapping("/student/home")
    public String studentHome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Student student = studentService.getStudentByUsername(username);
        model.addAttribute("student", student);

        return "test"; // Replace with the actual view name
    }
	
}
