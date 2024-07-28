package com.example.demo.controller;

import com.example.demo.entity.Question;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.util.ExcelHelper;
import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/excel")
public class ExcelController implements ServletContextAware {
    private ServletContext servletContext;
    private ExcelHelper excelHelper;
    // file controller
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "ExcelTest";
    }

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
//                List<Question> questions = excelHelper.readData(Question.class.getName());
//                System.out.println("list");
//                for(Question q: questions) {
//                    System.out.println(q.getQuestiontitle());
//                }
                List<Question> questions = new ArrayList<>();
                List<Map<String, String>> data = excelHelper.readData();

                System.out.println("Data from Excel file:");
                for (Map<String, String> row : data) {
                    Question question = Question.fromMap(row);
                    questions.add(question);
                    System.out.println(question.getQuestiontitle());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "ExcelTest";
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

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @GetMapping("/ExcelTest")
    public String etst(Model model) {
        return "ExcelTest";
    }



}
