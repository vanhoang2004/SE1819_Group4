package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.Class;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/student")
@Controller

public class StudentController {

    private SubjectService subjectService;
    private MockTestService mockTestService;
    private ChapterService chapterService;
    private MaterialService materialService;
    private StudentService studentService;
    private TeacherMaterialService teacherMaterialService;
    private TeacherPracticeService teacherPracticeService;
    private QuestionService questionService;
    private TeacherService teacherService;
    private ClassService classService;
    private TakenMockTestService takenMockTestService;

    private CompleteQuizService completeQuizService;


    @Autowired
    public StudentController(SubjectService subjectService, MockTestService mockTestService,
                             ChapterService chapterService, MaterialService materialService, StudentService studentService,
                             TeacherMaterialService teacherMaterialService, TeacherPracticeService teacherPracticeService,
                             QuestionService questionService, TeacherService teacherService, ClassService classService, TakenMockTestService takenMockTestService,
                             NotificationRepository nr, StudentRepository sr, CompleteQuizService completeQuizService) {
        this.subjectService = subjectService;
        this.mockTestService = mockTestService;
        this.chapterService = chapterService;
        this.materialService = materialService;
        this.studentService = studentService;
        this.teacherMaterialService = teacherMaterialService;
        this.teacherPracticeService = teacherPracticeService;
        this.questionService = questionService;
        this.teacherService= teacherService;
        this.classService=classService;
        this.takenMockTestService = takenMockTestService;
        this.sr = sr;
        this.nr = nr;

        this.completeQuizService=completeQuizService;
    }

    NotificationRepository nr;
    StudentRepository sr;

    @GetMapping("/home")
    public String home(HttpSession session, @RequestParam(value="search",required = false) String search, Model m){
        if(search != null) m.addAttribute("subjects",subjectService.search(search));
        else {
            List<Subject> subjects= subjectService.getAllSubject();
            m.addAttribute("subjects", subjects);

        }

        User u = (User) session.getAttribute("logged_user");
        Student s = sr.findStudentById(u.getUserId());
        Integer classCode = s.getSclass().getClassCode();

        List<Notification> allNotif = nr.findAllByClassCode(classCode);
        List<Notification> allRecent = new ArrayList<>();
        List<Notification> allOld = new ArrayList<>();
        allNotif.stream()
                .filter(n -> n.isRecent())
                .forEach(allRecent::add);
        allNotif.stream()
                .filter(n -> n.isOld())
                .forEach(allOld::add);

        List<Notification> generalNotif = nr.findAllGeneral();
        List<Notification> generalRecent = new ArrayList<>();
        List<Notification> generalOld = new ArrayList<>();
        generalNotif.stream()
                .filter(n -> n.isRecent())
                .forEach(generalRecent::add);
        generalNotif.stream()
                .filter(n -> n.isOld())
                .forEach(generalOld::add);

        List<Notification> classNotif = nr.findByClassCode(classCode);
        List<Notification> classRecent = new ArrayList<>();
        List<Notification> classOld = new ArrayList<>();
        classNotif.stream()
                .filter(n -> n.isRecent())
                .forEach(classRecent::add);
        classNotif.stream()
                .filter(n -> n.isOld())
                .forEach(classOld::add);

        session.setAttribute("all_recent",allRecent);
        session.setAttribute("all_old",allOld);
        session.setAttribute("gen_recent",generalRecent);
        session.setAttribute("gen_old",generalOld);
        session.setAttribute("class_recent",classRecent);
        session.setAttribute("class_old",classOld);
        return "student/home";
    }

    @GetMapping("/tester")
    public String test() {
        return "student/test";
    }

    @GetMapping("/mocktests/{subjectId}")
    public String listMockTestsBySubject(@PathVariable int subjectId, Model theModel) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<MockTest> mockTests = mockTestService.findMockTestBySubjectId(subjectId);
        Student student = studentService.getStudentByUsername(username);
        List<Pair<MockTest,Optional<TakenMockTest>>> mockTestCheck = new ArrayList<>();
        for (MockTest mockTest : mockTests) {
            TakenMockTest takenMockTest = takenMockTestService.getTakenMockTest(username, mockTest.getMocktestid());
            mockTestCheck.add(Pair.of(mockTest, Optional.ofNullable(takenMockTest)));
        }
        theModel.addAttribute("mockTestCheck",mockTestCheck);
        List<Chapter> chapters= chapterService.findChapterBySubjectId(subjectId);
        theModel.addAttribute("chapters", chapters);
        theModel.addAttribute("subjectId", subjectId);
        Subject subject = subjectService.getSubjectById(subjectId);
        if(subject==null) {

                throw new ApiRequestException("oops, no such subject found");
                //throw new IllegalStateException("oops, no such class")
        }
        theModel.addAttribute("subject", subject);
        List<TeacherMaterials> teachermaterial = teacherMaterialService.getAllTeachermaterialByClassAndSubject(student.getSclass().getClassCode(),subjectId);
        List<TeacherPractice> teacherpractice = teacherPracticeService.getTeacherPracticeByClassAndSubject(student.getSclass().getClassCode(), subjectId);
        System.out.println("ahihi");
        for(int i =0; i<teacherpractice.size();i++) {
            System.out.println(teacherpractice.get(i).getTitle());
        }
        theModel.addAttribute("teacherpractice",teacherpractice);
        theModel.addAttribute("classmaterial", teachermaterial);
        return "student/list";
    }

    @GetMapping("/materials")
    public String viewMaterials(@RequestParam("chapterId") int chapterId, @RequestParam("subjectId") int subjectId, Model model) {
        List<Material> materials = materialService.getAllMaterialBySubjectAndChapter(chapterId, subjectId);
        Chapter chapter=chapterService.getChapterByChapterId(chapterId);
        model.addAttribute("materials", materials);
        model.addAttribute("chapter", chapter);
        return "student/materials"; //
    }

    @GetMapping("/student/home")
    public String studentHome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Student student = studentService.getStudentByUsername(username);
        model.addAttribute("student", student);

        return "student/test"; // Replace with the actual view name
    }

    @GetMapping("/classmembers")
    public String classMembers(Model model, Principal principal) {
        String username = principal.getName();
        Student s = studentService.getStudentByUsername(username);
        List<Student> listStudent= studentService.getStudentInClass(s.getSclass().getClassCode());
        List<Integer> list = teacherService.getTeacherInClass(s.getSclass().getClassCode());
        List<Pair<Teacher, String>> listTeacher = new ArrayList<>();
        for (int i =0 ; i<list.size();i++) {
            Teacher teacher = teacherService.getTeacherByUserId(list.get(i));
            String subjectname= subjectService.getSubjectById(teacher.getSubject().getSubjectId()).getSubjectName();
            Pair<Teacher, String> teacherPair = Pair.of(teacher, subjectname);
            listTeacher.add(teacherPair);
        }
        Class cl = classService.getClassByClasscode(s.getSclass().getClassCode());
        model.addAttribute("classname", cl.getClassName());
        model.addAttribute("listTeacher", listTeacher);
        model.addAttribute("listStudent", listStudent);
        return "student/class-members";
    }

    @GetMapping("/xephang/{id}")
    public String xepHangMockTest(Model model, @PathVariable int id, Principal principal) {
        String username = principal.getName();
        Student s = studentService.getStudentByUsername(username);
        List<TakenMockTest> takenLop = takenMockTestService.getTakenMockTestByMockTestAndClass(s.getSclass().getClassCode(), id);
        List<TakenMockTest> takenTruong = takenMockTestService.getTakenMockTestByMockTest(id);
        List<Pair<TakenMockTest,String>> listStudentTakenMockTest = new ArrayList<>();
        for (int i =0 ; i<takenTruong.size();i++) {
            String classname = classService.getClassByClasscode(takenTruong.get(i).getStudent().getSclass().getClassCode()).getClassName();
            listStudentTakenMockTest.add(Pair.of(takenTruong.get(i), classname));

        }
        Class cla= classService.getClassByClasscode(s.getSclass().getClassCode());
        MockTest mt = mockTestService.getMockTestById(id);
        if(mt == null) {
            throw new ApiRequestException("oops, no such mock test found");
        }
        model.addAttribute("class", cla);
        model.addAttribute("mocktest", mt);
        model.addAttribute("username", username);
        model.addAttribute("xephangTruong", listStudentTakenMockTest);
        model.addAttribute("xephanglop",takenLop);
        System.out.println(takenLop.get(0).getStudent().getUser().getFullname());
        return "student/xephang";
    }
    @GetMapping("/progress/{subjectId}")
    public String getProgress(@PathVariable int subjectId, Principal principal, Model model) throws IOException {
        String username = principal.getName();
        Student student=studentService.getStudentByUsername(username);
        student.setSclass(null);
//    	String pieChartBase64 = chartService.generatePieChart(1, 2, 3);
        List<Chapter> chapterlist = chapterService.findChapterBySubjectId(subjectId);
        List<Pair<Chapter, Integer>> pieChartData= new ArrayList<Pair<Chapter,Integer>>();
        List<CompleteQuiz> listCompleteQuiz = completeQuizService.getCompleteQuizBySubjectIdAndUserid(subjectId, student.getUserId());
        for(Chapter chapter : chapterlist) {
            int i = completeQuizService.getCountQuizByUserAndChapter(chapter.getId(),student.getUserId());
            if(i>0) {
                pieChartData.add(Pair.of(chapter, i));
            }
        }
        List<Map<String, Object>> pieChartDataList = pieChartData.stream().map(pair -> {
            Map<String, Object> map = new HashMap<>();
            map.put("chapterId", pair.getFirst().getId());
            map.put("chapterName", pair.getFirst().getName());
            map.put("count", pair.getSecond());
            return map;
        }).collect(Collectors.toList());

        model.addAttribute("listCompleteQuiz",listCompleteQuiz);
//        String pieChartBase64 = chartService.generateDynamicPieChart(chapterlist, studentService.getStudentByUsername(username).getUserId());
//        model.addAttribute("pieChartBase64", pieChartBase64);
        //List<Integer> pieChartData =Arrays.asList(1,2,3);
        model.addAttribute("pieChartData",pieChartDataList);
        return "student/chart";
    }


}
