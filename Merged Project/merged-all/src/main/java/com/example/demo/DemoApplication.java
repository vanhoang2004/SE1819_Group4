package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


//		MockQuestionRepository mockquestion = null;
//		List<MockQuestion> mockQuestions = mockquestion.mockDetails(5);
//		MockQuestionKey key = new MockQuestionKey();
//		List<Integer> listID = new ArrayList<>();
//		for (MockQuestion mockQuestion : mockQuestions) {
//			Integer qID= mockQuestion.getQuestion().getId();
//			listID.add(qID);
//			key.setMocktestid(mockQuestion.getMockTest().getId());
//			key.setQuestionid(mockQuestion.getQuestion().getId());
//			System.out.println("+++++"+key.toString()+"++++++");
//			//mockquestion.deleteById(key);
//		}
//
//		for(Integer i: listID){
//			System.out.println("Questionid: "+i+"\n");
//		}
	}
}
