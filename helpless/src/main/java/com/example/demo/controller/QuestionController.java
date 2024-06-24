package com.example.demo.controller;

import java.security.Principal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Question;
import com.example.demo.service.MockTestService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TakenMocktestService;
import com.example.demo.utils.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller

public class QuestionController {
	private final QuestionService questionService;
	private final TakenMocktestService takenMocktestService;
	private final MockTestService mockTestService;
	
	@Autowired
	public QuestionController(QuestionService questionService, TakenMocktestService takenMocktestService,
			MockTestService mockTestService) {
		this.questionService = questionService;
		this.takenMocktestService = takenMocktestService;
		this.mockTestService = mockTestService;
	}
	@GetMapping("/change-username")
	public String setCookie(HttpServletResponse response) {
	    // create a cookie
	    Cookie cookie = new Cookie("username", "Jovan");

	    //add cookie to response
	    response.addCookie(cookie);

	    return "hehe";
	}
//    @GetMapping("/getmocktestquiz/{id}")
//	public String getQuestionByMockTest(@PathVariable int id, Model model, Principal principal, HttpServletRequest request) {
//    	String selected =null;
//    	Cookie[] cookies = request.getCookies();
//		String username = principal.getName();
//		if (cookies != null) {
//            for (Cookie cookie : cookies) {
//            	System.out.println(cookie.getName());
//                if ((username).equals(cookie.getName())) {
//                    selected = cookie.getValue();
//                    break;
//                }
//            }
//		}
//		else {
//			System.out.println("cookienul");
//		}
//		LocalDateTime starttime=LocalDateTime.now();
//		LocalDateTime endMockTest;
//		endMockTest = LocalDateTime.parse(mockTestService.getMockTestById(id).getEnd());
//		if(endMockTest == null) {
//			endMockTest=LocalDateTime.parse("2100-12-12T00:00:00");
//		}
//
//		takenMocktestService.saveMockTestStartime(username, id, starttime);
//    	List<Question> allQuestions = questionService.getQuestionByMocktest(id);
//		Collections.shuffle(allQuestions);
//
//		for (Question question : allQuestions) {
//	        shuffleOptions(question);
//	    }
//		String time="00:00:30";
//		model.addAttribute("endMockTest", endMockTest);
//		model.addAttribute("time", time);
//		model.addAttribute("questions", allQuestions);
//		model.addAttribute("mid", id);
//		model.addAttribute("username", username);
//		System.out.println("huhu"+selected);
//		if(selected != null ) {
//			ObjectMapper objectMapper = new ObjectMapper();
//		    Map<String, String> selectedAnswers = new HashMap<>();
//		    try {
//		            selectedAnswers = objectMapper.readValue(selected, new TypeReference<Map<String, String>>() {});
//		            for (Map.Entry<String, String> entry : selectedAnswers.entrySet()) {
//		                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//		            }
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }
//			model.addAttribute("selectedAnswers",selectedAnswers);
//		}
//
//	return "test";
//
//	}

	

	@GetMapping("/quiz/fetch-questions-for-user")
	public ResponseEntity<List<Question>> getQuestionForUser() {
		
		List<Question> allQuestions = questionService.getQuestionsForUser(10, 1, 1);
		List<Question> mutableQuestions = new ArrayList<>(allQuestions);
		Collections.shuffle(mutableQuestions);
		int availableQuestions = Math.min(3, mutableQuestions.size());
		List<Question> randomQuestions = mutableQuestions.subList(0, availableQuestions);
		 for (Question question : randomQuestions) {
		        shuffleOptions(question);
		    }
		return ResponseEntity.ok(randomQuestions);
	}
	private void shuffleOptions(Question question) {
	    List<String> options = new ArrayList<>();
	    
	    if (question.getOption1() != null) options.add(question.getOption1());
	    if (question.getOption2() != null) options.add(question.getOption2());
	    if (question.getOption3() != null) options.add(question.getOption3());
	    if (question.getOption4() != null) options.add(question.getOption4());

	    Collections.shuffle(options);

	    
	    int index = 0;
	    if (question.getOption1() != null) question.setOption1(options.get(index++));
	    if (question.getOption2() != null) question.setOption2(options.get(index++));
	    if (question.getOption3() != null) question.setOption3(options.get(index++));
	    if (question.getOption4() != null) question.setOption4(options.get(index++));
	}
	
//	@PostMapping("/submitPractice/{mid}")
//	public String submitPractice(@RequestParam Map<String, String> answers, Model model, @PathVariable int mid, Principal principal) {
//		List<Question> questions = questionService.getQuestionByMocktest(mid);
//		String username= principal.getName();
//		LocalDateTime endtime=LocalDateTime.now();
//		int correctCount=0;
//
//		for(Question question:questions) {
//			String userAnswer = answers.get(String.valueOf(question.getQuestionid()));
//			if(userAnswer != null && StringUtils.removeDiacritics(userAnswer.trim()).equalsIgnoreCase(StringUtils.removeDiacritics(question.getAnswer().trim()))) {
//				correctCount++;
//			}
//		}
//		int totalQuestion = questions.size();
//		float score = (float) correctCount/totalQuestion * 10;
//		takenMocktestService.saveMockTestEnd(username, mid, score, endtime);
//		model.addAttribute("correctCount", correctCount);
//        model.addAttribute("totalQuestion", totalQuestion);
//        model.addAttribute("score", score);
//
//        return "result";
//	}
//	@GetMapping("/teacherquiz/{id}")
//	public String getQuestionByTeacherPractice(@PathVariable int id, Model model) {
//		List<Question> allQuestions = questionService.getQuestionByTeacherPractice(id);
//		Collections.shuffle(allQuestions);
//
//		for (Question question : allQuestions) {
//	        shuffleOptions(question);
//	    }
//
//		model.addAttribute("questions", allQuestions);
//		model.addAttribute("tpid", id);
//
//	return "teacherpractice";
//	}
	
	@PostMapping("/submitTeacherPractice/{tpid}")
	public String submitTeacherPractice(@RequestParam Map<String, String> answers, Model model, @PathVariable int tpid) {
		List<Question> questions = questionService.getQuestionByTeacherPractice(tpid);
		int correctCount=0;
		for(Question question:questions) {
			String userAnswer = answers.get(String.valueOf(question.getQuestionid()));
			if(userAnswer != null && StringUtils.removeDiacritics(userAnswer.trim()).equalsIgnoreCase(StringUtils.removeDiacritics(question.getAnswer().trim()))) {
				correctCount++;
			}
		}
		int totalQuestion = questions.size();
		double score = (double) correctCount/totalQuestion * 10;
		model.addAttribute("correctCount", correctCount);
        model.addAttribute("totalQuestion", totalQuestion);
        model.addAttribute("score", score);
		
        return "result1";
	}
	
	
	
}
