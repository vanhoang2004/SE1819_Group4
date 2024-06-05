package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
    @Autowired
    UserRepository userRepository;

    public Integer getUserID(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user= userRepository.findUserByName(name);
		Integer userid = user.getId();
        return userid;
    }

    @GetMapping("/homepage")
    public String listH(Model model, @Param("keyword") String keyword) {
        Subject subjects = subject.findSubjectByUserID(getUserID());

        model.addAttribute("subject",subjects);
     List<MockTest> mockTests = mock.findMockTestByUserId(getUserID());
    if(keyword!=null){
        mockTests=mock.searchMockTest(getUserID(),keyword);
    }
        model.addAttribute("mock", mockTests);
        return "home";
    }

    @GetMapping("/questionbank")
    public String questionbank(Model model) {
       Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        List<Question> questions = question.findQuestionBySubjectID(getUserID());
        model.addAttribute("ques", questions);
        return "questionbank";
    }
    @PostMapping("/questionbank")
    public String getQuestion(@ModelAttribute Question questions){
        question.save(questions);
        return "redirect:/test/questionbank";
    }
    @GetMapping("questionbank/editquestion/{id}")
    public String list1(Model model,@PathVariable int id) {
      Question questions = question.findQuestionBYID(id);
        model.addAttribute("ques", questions);
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        return "edit-question";
    }
    @PostMapping("/editquestion")
    public String postQuestion(@ModelAttribute("ques") Question questions) {
        question.save(questions);
        return "redirect:/test/questionbank";
    }
    @GetMapping("materials/editmaterial/{id}")
    public String getMaterial(Model model,@PathVariable int id) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getId());
        model.addAttribute("chapter",chapters);
      Materials mater = material.findMaterialbyID(id);
        model.addAttribute("material",mater);
        return "edit-material";
    }
    @PostMapping("/editmaterial")
    public String postMaterial(@ModelAttribute("material") Materials materials) {
        material.save(materials);
        return "redirect:/test/materials";
    }
    @GetMapping("/materials/{id}")
    public String deleteMaterial(@ModelAttribute Materials materials,@PathVariable int id) {
material.deleteById(id);
    return "redirect:/test/materials";
    }
    @GetMapping("/questionbank/{id}")
    public String deleteQuestion(@ModelAttribute Question questions,@PathVariable int id) {
        mockquestion.deleteById(id);
        question.deleteById(id);
        return "redirect:/test/questionbank";
    }

    @GetMapping("/mockdetails/{id}")
    public String mockQuestion(Model model,@PathVariable Integer id) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapters> chapters = chapter.findChapterBySubject(subjects.getId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        List<Question> questions = question.mockTestDetails(id);
        model.addAttribute("ques", questions);
        Question q = new Question();
        model.addAttribute("nques",q);
        MockTest nMockTest= mock.mocktestbyID(id);
        model.addAttribute("mocktest",nMockTest);
        return "mockquestion";
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

        return "redirect:/test/mockdetails/" + id;    }

    @GetMapping("/materials")
    public String materrialList(Model model,@Param("keyword") String keyword) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
     List<Chapters> chapters = chapter.findChapterBySubject(subjects.getId());
        model.addAttribute("chapter",chapters);

        List<Materials> mater = material.findMaterialsbySubject(getUserID());
        if(keyword!=null){
            mater=material.searchMaterial(getUserID(),keyword);
        }
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
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);

        // ghep code thi sua
        //List<MockTest> mockTests = mock.findMockTestByUserId(manager.getSubjectid());
        List<MockTest> mockTests = mock.findMockTestByUserId(getUserID());

        model.addAttribute("mock", mockTests);

        return "list";
    }
     @PostMapping("/mocktests")
    public String postMockTests(@ModelAttribute MockTest mockTest) {
        mock.save(mockTest);
        return "redirect:/test/mocktests";
    }

    @GetMapping("/changepassword")
    public  String changepass(Model model){
        model.addAttribute("change", new ChangePass());
        return "changepassword";
    }


    @PostMapping("/changepassword")
    public String changePassWord(@ModelAttribute("change") ChangePass change,Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user= userRepository.findUserByName(name);

        if(!user.getPassword().equals("{noop}"+change.getOldpassword())){
            model.addAttribute("error","Incorrect Password");
            return "changepassword";
        }
        else
            if(!change.getNewpassword().equals(change.getReenter())){
                model.addAttribute("error","Password does not match");
                return "changepassword";
            }
        user.setPassword("{noop}"+change.getNewpassword());
        userRepository.save(user);
        return "redirect:/test/homepage";

    }

}
