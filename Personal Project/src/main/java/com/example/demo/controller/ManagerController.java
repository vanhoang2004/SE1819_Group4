package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping ("/manager")
public class ManagerController {
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
    @Autowired
    UserRepository userRepository;

    public Integer getUserID(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user= userRepository.findUserByUsername(name);
		Integer userid = user.getUserId();
        return userid;
    }

    @GetMapping("/home")
    public String listH(Model model, @Param("keyword") String keyword) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<MockTest> mockTests = mock.findMockTestByUserId(getUserID());
        if(keyword!=null){
            mockTests=mock.searchMockTest(getUserID(),keyword);
        }
        model.addAttribute("mock", mockTests);
        return "manager/home";
    }

    @GetMapping("/questionbank")
    public String questionbank(Model model,@Param("keyword" )String keyword,@RequestParam(value="filter",required = false) Integer chapterID) {
       Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        List<Question> questions = question.findQuestionBySubjectID(getUserID());
        System.out.println("-------------------------"+chapterID);
        if(keyword!=null || chapterID != null){
            System.out.println(chapterID);
            questions=question.searchQuestion(getUserID(),keyword,chapterID);
        }
        model.addAttribute("ques", questions);
        return "manager/question-bank";
    }

    @PostMapping("/questionbank")
    public String getQuestion(@ModelAttribute Question questions){
        question.save(questions);
        return "redirect:/manager/questionbank";
    }

    @GetMapping("questionbank/editquestion/{id}")
    public String list1(Model model,@PathVariable int id) {
        Question questions = question.findQuestionBYID(id);
        model.addAttribute("ques", questions);
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        return "manager/edit-question";
    }

    @PostMapping("/editquestion")
    public String postQuestion(@ModelAttribute("ques") Question questions) {
        question.save(questions);
        return "redirect:/manager/questionbank";
    }

    @GetMapping("materials/editmaterial/{id}")
    public String getMaterial(Model model,@PathVariable int id) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
         Materials mater = material.findMaterialbyID(id);
        model.addAttribute("material",mater);
        return "manager/edit-material";
    }

    @PostMapping("/editmaterial")
    public String postMaterial(@ModelAttribute("material") Materials materials) {
        material.save(materials);
        return "redirect:/manager/materials";
    }

    @GetMapping("/materials/{id}")
    public String deleteMaterial(@ModelAttribute Materials materials,@PathVariable Integer id) {
        material.deleteById(id);
        return "redirect:/manager/materials";
    }

    @Transactional
    @GetMapping("/questionbank/{id}")
    public String deleteQuestion(@ModelAttribute Question questions,@PathVariable Integer id) {
        List<MockQuestion> mockque= mockquestion.MockTestByQuestion(id);
        MockQuestionKey key = new MockQuestionKey();
        if(mockque==null) question.deleteById(id);
        for(MockQuestion i: mockque){
            key.setMocktestid(i.getMockTest().getId());
            key.setQuestionid(i.getQuestion().getId());
            mockquestion.deleteById(key);
        }
        question.deleteById(id);
        return "redirect:/manager/questionbank";
    }

    @GetMapping("/mockdetails/{id}")
    public String mockQuestion(Model model,@PathVariable Integer id) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        List<Question> questions = question.mockTestDetails(id);
        model.addAttribute("ques", questions);
        Question q = new Question();
        model.addAttribute("nques",q);
        MockTest nMockTest= mock.mocktestbyID(id);
        model.addAttribute("mocktest",nMockTest);
        return "manager/mock-question";
    }

    @Transactional
    @GetMapping("/mockdetails/{id}/{questionid}")
    public String deleteQuestionDetails(@ModelAttribute Question questions,@PathVariable("id") Integer id,@PathVariable("questionid") Integer questionid) {
        List<MockQuestion> mockque= mockquestion.MockTestByQuestion(questionid);
        MockQuestionKey key = new MockQuestionKey();
        if(mockque==null)
            question.deleteById(questionid);
        for(MockQuestion i: mockque){
            key.setMocktestid(i.getMockTest().getId());
            key.setQuestionid(i.getQuestion().getId());
            mockquestion.deleteById(key);
        }
        question.deleteById(questionid);
        return "redirect:/manager/mockdetails/"+id;
    }

    @PostMapping("/mockdetails/{id}")
    public String addMockquestion(@ModelAttribute("nques") Question questions,@PathVariable Integer id){
        question.save(questions);
        MockTest nMockTest= mock.mocktestbyID(id);
        MockQuestionKey mockQuestionKey = new MockQuestionKey(nMockTest.getId(), questions.getId());
        MockQuestion mockques = new MockQuestion();
        mockques.setId(mockQuestionKey);
        mockques.setMockTest(nMockTest);
        mockques.setQuestion(questions);
        System.out.println("\n=== New MockQuestion: " + mockques);
     mockquestion.save(mockques);
        return "redirect:/manager/mockdetails/" + id;
    }
    @GetMapping("/materials")
    public String materrialList(Model model,@Param("keyword") String keyword) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Materials> mater = material.findMaterialsbySubject(getUserID());
        if(keyword!=null) mater=material.searchMaterial(getUserID(),keyword);
        model.addAttribute("material",mater);
        return "manager/list-materials";
    }
    @PostMapping("/materials")
    public String getMaterials(@ModelAttribute Materials materials){
        material.save(materials);
        return "redirect:/manager/materials";
    }

    @GetMapping("/mocktests")
    public String getMockTests(Model model,@ModelAttribute Manager manager ) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        // ghep code thi sua
        //List<MockTest> mockTests = mock.findMockTestByUserId(manager.getSubjectid());
        List<MockTest> mockTests = mock.findMockTestByUserId(getUserID());
        model.addAttribute("mock", mockTests);
        return "manager/list-mocktests";
    }

    @PostMapping("/mocktests")
    public String postMockTests(@ModelAttribute MockTest mockTest) {
        mock.save(mockTest);
        return "redirect:/manager/mocktests";
    }
}
