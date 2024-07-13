package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.Question;
import com.example.demo.helpers.ExcelHelper;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/excelapprove")

//declare copy from old controller
public class ExcelApproveController implements ServletContextAware {
    SubjectRepository su;
    ClassRepository clas;
    MockTestRepository mt;
    TeacherRepository tr;
    StudentRepository sr;
    MaterialRepository mr;
    TeacherMaterialsRepository tmr;
    TeacherPracticeRepository tpr;
    QuestionRepository qr;
    LevelRepository lr;
    ChapterRepository cr;

    @Autowired
    public ExcelApproveController(SubjectRepository su, ClassRepository clas, MockTestRepository mt,
                               StudentRepository sr, MaterialRepository mr, TeacherRepository tr,
                               TeacherMaterialsRepository tmr, TeacherPracticeRepository tpr,
                               QuestionRepository qr, LevelRepository lr, ChapterRepository cr) {
        this.su = su;
        this.clas = clas;
        this.mt = mt;
        this.sr = sr;
        this.mr = mr;
        this.tr = tr;
        this.tmr = tmr;
        this.tpr = tpr;
        this.qr = qr;
        this.lr = lr;
        this.cr = cr;
    }

    //declare for the excel reading
    private ServletContext servletContext;
    private ExcelHelper excelHelper;


    // file controller
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "ExcelTest2";
    }


    //process the submit file
    @RequestMapping(value = "process", method = RequestMethod.POST)
    public String process(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            String fileName = uploadExcelFile(file);
            if (fileName != null) {
                System.out.println("Uploaded file name: " + fileName);

                // Use the absolute path specified
                String excelPath = "D:\\helpless\\src\\main\\resources\\excels\\" + fileName;
                System.out.println("Excel file path: " + excelPath);

                ExcelHelper excelHelper = new ExcelHelper(excelPath);

                List<Question> questions = new ArrayList<>();
                List<Map<String, String>> data = excelHelper.readData();

                System.out.println("Data from Excel file:");
                for (Map<String, String> row : data) {
                    Question question = Question.fromMap(row);
                    //add in list to show on console
                    questions.add(question);

                    //fix cung
                    question.setChapterid(2);
                    question.setLevelid(2);
                    question.setSubjectid(2);
                    question.setStatus(2);

                    //add vao db
                    qr.save(question);
                    System.out.println(question.getQuestiontitle());
                }

                //delete all imported files after insert
                deleteAllFilesInDirectory("D:\\helpless\\src\\main\\resources\\excels");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "redirect:/teacher/questionbank/" + 2;
    }

    private String uploadExcelFile(MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            // Use the absolute path specified
            String uploadDir = "D:\\helpless\\src\\main\\resources\\excels";
            Path path = Paths.get(uploadDir, multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            return multipartFile.getOriginalFilename();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //function delete file
    private void deleteAllFilesInDirectory(String directory) throws IOException {
        Path directoryPath = Paths.get(directory);

        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
            Files.walkFileTree(directoryPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

//    //created for playful :)))
//    @GetMapping("/ExcelTest")
//    public String etst(Model model) {
//        return "ExcelTest2";
//    }

    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        Resource resource = new ClassPathResource("/static/ques3.xlsx");
        Path path = Paths.get(resource.getURI());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header("Content-Disposition", "attachment; filename=\"ques3.xlsx\"")
                .body(resource);
    }
}
