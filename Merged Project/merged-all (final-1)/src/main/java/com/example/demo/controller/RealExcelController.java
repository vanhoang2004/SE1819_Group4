package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.Chapter;
import com.example.demo.entity.Level;
import com.example.demo.entity.Question;
import com.example.demo.entity.Subject;
import com.example.demo.util.ExcelHelper;
import jakarta.servlet.ServletContext;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

@Controller
@RequestMapping("/realexcel")

//declare copy from old controller
public class RealExcelController implements ServletContextAware {
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

    public RealExcelController() {
    }

    @Autowired
    public RealExcelController(SubjectRepository su, ClassRepository clas, MockTestRepository mt, TeacherRepository tr, StudentRepository sr, MaterialRepository mr, TeacherMaterialsRepository tmr, TeacherPracticeRepository tpr, QuestionRepository qr, LevelRepository lr, ChapterRepository cr) {
        this.su = su;
        this.clas = clas;
        this.mt = mt;
        this.tr = tr;
        this.sr = sr;
        this.mr = mr;
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
        return "ExcelTest";
    }


    //process the submit file
    @RequestMapping(value = "process", method = RequestMethod.POST)
    public String readExcel( @RequestParam("file") MultipartFile file) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        int subjectid = tr.findSubjectIdByUsername(name);
        List<Question> questionsList = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream(); XSSFWorkbook wb = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = wb.getSheetAt(0);
            FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator(); // Giả sử là trang tính đầu tiên
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Bỏ qua hàng tiêu đề nếu có
                    continue;
                }
                Question ques = new Question();
                ques.setQuestiontitle(getCellValueAsString(row.getCell(0)));
                ques.setOption1(getCellValueAsString(row.getCell(2)));
                ques.setOption2(getCellValueAsString(row.getCell(3)));
                ques.setOption3(getCellValueAsString(row.getCell(4)));
                ques.setOption4(getCellValueAsString(row.getCell(5)));
                ques.setAnswer(getCellValueAsString(row.getCell(6)));
                ques.setLevelid(checkLevel(getCellValueAsString(row.getCell(8))));
                ques.setChapterid(checkChapter(getCellValueAsString(row.getCell(7))));
                questionsList.add(ques);
                for (Question questions : questionsList) {
                    questions.setStatus(1);
                    questions.setUsername(name);
                    questions.setSubjectid(subjectid);

                    qr.save(questions);
                }
            }
        }
        catch (Exception e) {
            throw new IOException("Có lỗi xảy ra khi đọc tệp Excel. Vui lòng kiểm tra lại định dạng tệp và thử lại.", e);
        }

        return "redirect:/teacher/questionbank/2" ;
    }
    private Integer checkLevel(String levelName){
        return lr.getLevelID(levelName);
    }

    private Integer checkChapter(String chapterName){
        return cr.getChapterIDByName(chapterName);
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
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

    //created for playful :)))
    @GetMapping("/ExcelTest")
    public String etst(Model model) {
        return "ExcelTest";
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        Resource resource = new ClassPathResource("/static/questionTemplate.xlsx");
        Path path = Paths.get(resource.getURI());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header("Content-Disposition", "attachment; filename=\"ques3.xlsx\"")
                .body(resource);
    }

}
