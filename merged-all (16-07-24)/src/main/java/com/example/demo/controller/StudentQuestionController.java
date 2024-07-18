package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import com.example.demo.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class StudentQuestionController {

    private final QuestionService questionService;
    private final TakenMockTestService takenMocktestService;
    private final MockTestService mockTestService;
    private final StudentService studentService;
    private final TeacherPracticeService teacherPracticeService;
    private final StudentTakenPracticeService studentTakenPracticeService;
    private final ChapterService chapterService;
    private final SubjectService subjectService;
    private final PriorityWeightService priorityWeightService;
    private final CompleteQuizService completeQuizService;

    @Autowired
    public StudentQuestionController(QuestionService questionService, TakenMockTestService takenMocktestService,
                              MockTestService mockTestService, StudentService studentService,
                              TeacherPracticeService teacherPracticeService, StudentTakenPracticeService studentTakenPracticeService, ChapterService chapterService,
                              SubjectService subjectService, PriorityWeightService priorityWeightService, CompleteQuizService completeQuizService) {

        this.questionService = questionService;
        this.takenMocktestService = takenMocktestService;
        this.mockTestService = mockTestService;
        this.studentService = studentService;
        this.teacherPracticeService = teacherPracticeService;
        this.studentTakenPracticeService = studentTakenPracticeService;
        this.chapterService=chapterService;
        this.subjectService=subjectService;
        this.priorityWeightService=priorityWeightService;
        this.completeQuizService=completeQuizService;
    }

    @GetMapping("/getmocktestquiz/{id}")
    public String getQuestionByMockTest(@PathVariable int id, Model model, Principal principal, HttpServletRequest request) {


        String username = principal.getName();

        TakenMockTest takenMockTest = takenMocktestService.getTakenMockTest(username, id);
        if (takenMockTest != null && takenMockTest.getScore() != null) {
            // Redirect to a page informing the student they have already completed the test
            return "examcompleted";
        }
        LocalDateTime starttime=LocalDateTime.now();
        LocalDateTime endMockTest;
        endMockTest = mockTestService.getMockTestById(id).getEnd();
        if(endMockTest == null) {
            endMockTest=LocalDateTime.parse("2100-12-12T00:00:00");
        }

        takenMocktestService.saveMockTestStartime(username, id, starttime);
        List<Question> allQuestions = questionService.getQuestionByMocktest(id);
        Collections.shuffle(allQuestions);

        for (Question question : allQuestions) {
            shuffleOptions(question);
        }
        MockTest mt = mockTestService.getMockTestById(id);
        model.addAttribute("mocktest", mt);
        String time="00:05:00";
        model.addAttribute("endMockTest", endMockTest);
        model.addAttribute("time", time);
        model.addAttribute("questions", allQuestions);
        model.addAttribute("mid", id);
        model.addAttribute("username", username);


        return "test";

    }







    //	@GetMapping("/quiz/fetch-questions-for-user")
//	public ResponseEntity<List<Question>> getQuestionForUser() {
//
//		List<Question> allQuestions = questionService.getQuestionsForUser(10, 1, 1);
//		List<Question> mutableQuestions = new ArrayList<>(allQuestions);
//		Collections.shuffle(mutableQuestions);
//		int availableQuestions = Math.min(3, mutableQuestions.size());
//		List<Question> randomQuestions = mutableQuestions.subList(0, availableQuestions);
//		 for (Question question : randomQuestions) {
//		        shuffleOptions(question);
//		    }
//		return ResponseEntity.ok(randomQuestions);
//	}
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

    @PostMapping("/submitPractice/{mid}")
    public String submitPractice(@RequestParam Map<String, String> answers, Model model, @PathVariable int mid, Principal principal) {
        List<Question> questions = questionService.getQuestionByMocktest(mid);
        String username= principal.getName();
        LocalDateTime endtime=LocalDateTime.now();
        int correctCount=0;
        List<Map<String, Object>> resultDetails = new ArrayList<>();
        for (Question question : questions) {
            String userAnswer = answers.get(String.valueOf(question.getQuestionid()));
            boolean isCorrect = userAnswer != null && StringUtils.removeDiacritics(userAnswer.trim()).equalsIgnoreCase(StringUtils.removeDiacritics(question.getAnswer().trim()));

            Map<String, Object> questionDetails = new HashMap<>();
            questionDetails.put("questionContent", question.getQuestiontitle());
            questionDetails.put("userAnswer", userAnswer != null ? userAnswer : "Chưa chọn đáp án");
            questionDetails.put("isCorrect", isCorrect);
            questionDetails.put("correctAnswer", question.getAnswer());

            resultDetails.add(questionDetails);

            if (isCorrect) {
                correctCount++;
            }
        }
        int totalQuestion = questions.size();
        float score = (float) correctCount/totalQuestion * 10;
        takenMocktestService.saveMockTestEnd(username, mid, score, endtime);
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("totalQuestion", totalQuestion);
        model.addAttribute("score", score);
        model.addAttribute("resultDetails", resultDetails);
        return "result";
    }
    @GetMapping("/teacherquiz/{id}")
    public String getQuestionByTeacherPractice(@PathVariable int id, Model model, Principal principal) {
        List<Question> allQuestions = questionService.getQuestionByTeacherPractice(id);
        Collections.shuffle(allQuestions);

        for (Question question : allQuestions) {
            shuffleOptions(question);
        }
        String username = principal.getName();
        model.addAttribute("username", username);
        model.addAttribute("questions", allQuestions);
        model.addAttribute("tpid", id);

        return "teacherpractice";
    }

    @PostMapping("/submitTeacherPractice/{tpid}")
    public String submitTeacherPractice(@RequestParam Map<String, String> answers, Model model, @PathVariable int tpid, Principal principal) {
        String username = principal.getName();
        List<Question> questions = questionService.getQuestionByTeacherPractice(tpid);
        int correctCount=0;
        List<Map<String, Object>> resultDetails = new ArrayList<>();
        for (Question question : questions) {
            String userAnswer = answers.get(String.valueOf(question.getQuestionid()));
            boolean isCorrect = userAnswer != null && StringUtils.removeDiacritics(userAnswer.trim()).equalsIgnoreCase(StringUtils.removeDiacritics(question.getAnswer().trim()));

            Map<String, Object> questionDetails = new HashMap<>();
            questionDetails.put("questionContent", question.getQuestiontitle());
            questionDetails.put("userAnswer", userAnswer != null ? userAnswer : "Chưa chọn đáp án");
            questionDetails.put("isCorrect", isCorrect);
            questionDetails.put("correctAnswer", question.getAnswer());

            resultDetails.add(questionDetails);

            if (isCorrect) {
                correctCount++;
            }
        }
        Student s= studentService.getStudentByUsername(username);
        TeacherPractice tpractice = teacherPracticeService.getTeacherPracticeById(tpid);

        int totalQuestion = questions.size();
        float score = (float) correctCount/totalQuestion * 10;
        StudentTakenPractice studentTakenPractice = new StudentTakenPractice(s, tpractice, score);
        studentTakenPracticeService.saveStudentTakenPractice(studentTakenPractice);
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("totalQuestion", totalQuestion);
        model.addAttribute("score", score);
        model.addAttribute("resultDetails", resultDetails);

        return "result1";
    }

    @GetMapping("/chapterquiz")
    public String getQuestionByChapterQuiz(@RequestParam("chapterId") int chapterId, @RequestParam("subjectId") int subjectId, HttpSession session, Model model, Principal principal) {
        String username = principal.getName();
        Student s=studentService.getStudentByUsername(username);
        List<Question> allQuestionsLevel1Weight1 = questionService.getQuestionsForUserWithLevelAndWeight1(10,s.getUserId(), subjectId, chapterId, 1);
        List<Question> allQuestionsLevel2Weight1 = questionService.getQuestionsForUserWithLevelAndWeight1(10,s.getUserId(), subjectId, chapterId, 2);
        List<Question> allQuestionsLevel3Weight1 = questionService.getQuestionsForUserWithLevelAndWeight1(10,s.getUserId(), subjectId, chapterId, 3);
        Collections.shuffle(allQuestionsLevel1Weight1);
        Collections.shuffle(allQuestionsLevel2Weight1);
        Collections.shuffle(allQuestionsLevel3Weight1);
        if(allQuestionsLevel1Weight1.size()<5) {
            List<Question> allQuestionsLevel1Weight0 = questionService.getQuestionsForUserWithLevelAndWeight0(10,s.getUserId(), subjectId, chapterId, 1);
            allQuestionsLevel1Weight1.addAll(allQuestionsLevel1Weight0.subList(0, Math.min(5-allQuestionsLevel1Weight1.size(), allQuestionsLevel1Weight0.size())));
        }
        if(allQuestionsLevel2Weight1.size()<5) {
            List<Question> allQuestionsLevel2Weight0 = questionService.getQuestionsForUserWithLevelAndWeight0(10,s.getUserId(), subjectId, chapterId, 2);
            allQuestionsLevel1Weight1.addAll(allQuestionsLevel2Weight0.subList(0, Math.min(5-allQuestionsLevel2Weight1.size(), allQuestionsLevel2Weight0.size())));
        }
        if(allQuestionsLevel3Weight1.size()<5) {
            List<Question> allQuestionsLevel3Weight0 = questionService.getQuestionsForUserWithLevelAndWeight0(10,s.getUserId(), subjectId, chapterId, 3);
            allQuestionsLevel1Weight1.addAll(allQuestionsLevel3Weight0.subList(0, Math.min(5-allQuestionsLevel3Weight1.size(), allQuestionsLevel3Weight0.size())));
        }
        List<Question> allQuestions = new ArrayList<>();
        allQuestions.addAll(allQuestionsLevel1Weight1);
        allQuestions.addAll(allQuestionsLevel2Weight1);
        allQuestions.addAll(allQuestionsLevel3Weight1);

        List<Question> mutableQuestions = new ArrayList<>(allQuestions);
        Collections.shuffle(mutableQuestions);
        int availableQuestions = Math.min(15, mutableQuestions.size());
        List<Question> randomQuestions = mutableQuestions.subList(0, availableQuestions);
        for (Question question : randomQuestions) {
            shuffleOptions(question);
        }
        Chapter chapter =chapterService.getChapterByChapterId(chapterId);
        Subject subject = subjectService.getSubjectById(subjectId);
        model.addAttribute("chapterid", chapterId);
        model.addAttribute("chapter", chapter);
        model.addAttribute("subject", subject);
        model.addAttribute("time", "00:30:00");
        model.addAttribute("questions", randomQuestions);
        session.setAttribute("questions_"+username+"_"+chapterId, randomQuestions);
        return "chapterquiz";
    }

    @PostMapping("/submitChapterQuiz")
    public String submitChapterQuiz(@RequestParam Map<String, String> answers, @RequestParam("chapterid") int chapterid,  HttpSession session, Model model, Principal principal) {
        String username = principal.getName();
        User user=studentService.getStudentByUsername(username).getUser();
        List<Question> questions = (List<Question>) session.getAttribute("questions_"+username+"_"+chapterid);
        int correctCount=0;
        List<Map<String, Object>> resultDetails = new ArrayList<>();
        List<Integer> listCorrect = new ArrayList<Integer>();
        for (Question question : questions) {
            String userAnswer = answers.get(String.valueOf(question.getQuestionid()));
            boolean isCorrect = userAnswer != null && StringUtils.removeDiacritics(userAnswer.trim()).equalsIgnoreCase(StringUtils.removeDiacritics(question.getAnswer().trim()));
            if(isCorrect) listCorrect



                    .add(question.getQuestionid());
            Map<String, Object> questionDetails = new HashMap<>();
            questionDetails.put("questionContent", question.getQuestiontitle());
            questionDetails.put("questionImage", question.getImage());
            questionDetails.put("userAnswer", userAnswer != null ? userAnswer : "Chưa chọn đáp án");
            questionDetails.put("isCorrect", isCorrect);
            questionDetails.put("correctAnswer", question.getAnswer());

            resultDetails.add(questionDetails);

            if (isCorrect) {
                correctCount++;
            }
        }
        Student student = studentService.getStudentByUsername(username);
        Chapter chapter = chapterService.getChapterByChapterId(chapterid);
        LocalDate submitDate = LocalDate.now();

        priorityWeightService.updatePriorityWeight(user.getUserId(), listCorrect);
        int totalQuestion = questions.size();
        float score = (float) correctCount/totalQuestion * 10;
        System.out.println(chapter.getName());
        CompleteQuiz completeQuiz = new CompleteQuiz();
        completeQuiz.setStudent(student);
        completeQuiz.setChapter(chapter);
        completeQuiz.setScore(score);
        completeQuiz.setTimeComplete(submitDate);

        completeQuizService.saveCompleteQuiz(completeQuiz);
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("totalQuestion", totalQuestion);
        model.addAttribute("score", score);
        model.addAttribute("resultDetails", resultDetails);
        return "result2";
    }
}
