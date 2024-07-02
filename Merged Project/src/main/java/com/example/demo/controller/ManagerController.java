package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import com.example.demo.util.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping ("/test")
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
    @Autowired
    TakenMockTestRepository takenMockTestRepository;

    public Integer getUserID(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user= userRepository.findUserByUsername(name);
		Integer userid = user.getUserId();
        return userid;
    }


    private Integer checkLevel(String levelName){
        return level.getLevelID(levelName);
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
    //

    //function delete file after save
//    private void deleteAllFilesInDirectory(String directory) throws IOException {
//        Path directoryPath = Paths.get(directory);
//
//        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
//            Files.walkFileTree(directoryPath, new SimpleFileVisitor<Path>() {
//                @Override
//                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                    Files.delete(file);
//                    return FileVisitResult.CONTINUE;
//                }
//            });
//        }
//    }


    //Process

    // Upload Excel
//    private String uploadExcelFile(MultipartFile multipartFile) {
//        try {
//            byte[] bytes = multipartFile.getBytes();
//            // Use the absolute path specified
//            String uploadDir = "D:\\helpless\\src\\main\\resources\\excels";
//            Path path = Paths.get(uploadDir, multipartFile.getOriginalFilename());
//            Files.write(path, bytes);
//            return multipartFile.getOriginalFilename();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    //String filePath = "C:/Users/Admin/Desktop/New/" + file;
    //Read Excel

    public List<Question> readExcel(String file) throws IOException {
        List<Question> questionsList = new ArrayList<>();
        String filePath = "C:/Users/Admin/Downloads/" + file;
        FileInputStream fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook wb= new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = wb.getSheetAt(0);
        FormulaEvaluator formula =wb.getCreationHelper().createFormulaEvaluator();// Giả sử là trang tính đầu tiên
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Bỏ qua hàng tiêu đề nếu có
                    continue;
                }
                Question ques = new Question();
                ques.setTitle(getCellValueAsString(row.getCell(0)));
                ques.setOp1(getCellValueAsString(row.getCell(2)));
                ques.setOp2(getCellValueAsString(row.getCell(3)));
                ques.setOp3(getCellValueAsString(row.getCell(4)));
                ques.setOp4(getCellValueAsString(row.getCell(5)));
                ques.setAns(getCellValueAsString(row.getCell(6)));
                ques.setLevelID(checkLevel(getCellValueAsString(row.getCell(7))));
                questionsList.add(ques);
            }
            wb.close();
            fileInputStream.close();
        return questionsList;
    }

    public static String[] Headers={
                "UserName",
            "Score"
    };

    @GetMapping("/downloadExcel")
    public void CreateExcel() throws FileNotFoundException {
        try{
            XSSFWorkbook wb= new XSSFWorkbook();
            Sheet sheet = wb.createSheet("questionTemplate");
            // Create header row
            Row row = sheet.createRow(0);
            for (int i = 0; i < Headers.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(Headers[i]);
            }
            FileOutputStream out= new FileOutputStream(new File("C:/Users/Admin/Desktop/New/result.xlsx"));
            wb.write(out);
            out.close();

        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Excel file created");
    }

    @GetMapping("/homepage")
    public String listMockTest(Model model, @Param("keyword") String keyword) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
     List<MockTest> mockTests = mock.findMockTestByUserId(getUserID());
    if(keyword!=null){
        mockTests=mock.searchMockTest(getUserID(),keyword);
    }
        model.addAttribute("mock", mockTests);
        return "manager/home";
    }

    private Page<Question> getQuestion(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return question.findQuestionBySubjectID(getUserID(),pageable);
    }

    @GetMapping("/questionbank")
    public String questionbank(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               Model model,
                               @Param("keyword")String keyword,
                               @RequestParam(value="filter", required = false) Integer chapterID) {
       Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapter> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
//       List<Question> questions = question.findQuestionBySubjectID(getUserID());
//        if(keyword!=null || chapterID != null){
//            System.out.println(chapterID);
//            questions=question.searchQuestion(getUserID(),keyword,chapterID);
//        }
        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero");
        }
        Page<Question> questions = getQuestion(page,size);

        if (keyword != null || chapterID != null) {
            questions = question.searchQuestion(getUserID(), keyword, chapterID, PageRequest.of(page, size));
        }
        model.addAttribute("ques", questions);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", questions.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        model.addAttribute("filter", chapterID);
        return "manager/questionbank";
    }

    @PostMapping("/questionbank")
    public String getQuestion(@ModelAttribute(name="ques") Question question,
                              @RequestParam(value = "file", required = false) String file,
                              @RequestParam(value = "fileImage", required = false) MultipartFile multipartFile,

                              Model model)throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

//        if (!question.getTitle().isEmpty()) {
            question.setImage(fileName);
            Question saveQues= this.question.save(question);

        String uploadDir ="./questionbank/"+ saveQues.getId();
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
try(InputStream inputStream = multipartFile.getInputStream();){
    Path filePath= uploadPath.resolve(fileName);
    System.out.println(filePath.toFile().getAbsoluteFile());
    Files.copy(inputStream,filePath,StandardCopyOption.REPLACE_EXISTING);
} catch (IOException e){
    throw new IOException("Could not save uploadfile: "+fileName);
}





//        }

        if (file!=null && !file.isEmpty()) {
            List<Question> list = readExcel(file);
            for (Question q : list) {
                q.setSubjectID(question.getSubjectID());
                q.setChapterID(question.getChapterID());
                q.setLevelID(question.getLevelID());
                q.setStatus(question.getStatus());
                this.question.save(q);
            }
            throw new IllegalArgumentException("Answer must be one of the options");

        }
        return "redirect:/test/questionbank";

//
//        try {
//            if (!question.getTitle().isEmpty()) {
//                this.question.save(question);
//            }
//            if (file != null && !file.isEmpty()) {
//                List<Question> list = readExcel(file);
//                for (Question q : list) {
//                    q.setSubjectID(question.getSubjectID());
//                    q.setChapterID(question.getChapterID());
//                    q.setLevelID(question.getLevelID());
//                    q.setStatus(question.getStatus());
//
//                    // Validate that answer is one of the options
//                    if (!q.getAns().equals(q.getOp1()) &&
//                            !q.getAns().equals(q.getOp2()) &&
//                            !q.getAns().equals(q.getOp3()) &&
//                            !q.getAns().equals(q.getOp4())) {
//                        throw new IllegalArgumentException("Answer must be one of the options");
//                    }
//
//                    this.question.save(q);
//                }
//            }
//
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//            model.addAttribute("error",e.getMessage());// Log the validation error
//            return "redirect:/test/questionbank";
//
//        }
//        return "redirect:/test/questionbank";
    }

    @GetMapping("questionbank/editquestion/{id}")
    public String list1(Model model,@PathVariable int id) {
      Question questions = question.findQuestionBYID(id);
        model.addAttribute("ques", questions);
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapter> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        return "manager/edit-question";
    }

//    @PostMapping("/editquestion")
//    public String postQuestion(@ModelAttribute("ques") Question questions,
//                               @RequestParam(value = "fileImage", required = false) MultipartFile multipartFile
//                               ) throws IOException {
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//
////        if (!question.getTitle().isEmpty()) {
//
//        if (!fileName.isEmpty()|| fileName!=null){
//            questions.setImage(fileName);
//            Question saveQues= this.question.save(questions);
//            String uploadDir ="./questionbank/"+ saveQues.getId();
//            Path uploadPath = Paths.get(uploadDir);
//            if(!Files.exists(uploadPath)){
//                Files.createDirectories(uploadPath);
//            }
//            try(InputStream inputStream = multipartFile.getInputStream();){
//                Path filePath= uploadPath.resolve(fileName);
//                System.out.println(filePath.toFile().getAbsoluteFile());
//                Files.copy(inputStream,filePath,StandardCopyOption.REPLACE_EXISTING);
//            } catch (IOException e){
//                throw new IOException("Could not save uploadfile: "+fileName);
//            }
//        }
//
//        return "redirect:/test/questionbank";
//    }
@PostMapping("/editquestion")
public String postQuestion(@ModelAttribute("ques") Question questions,
                           @RequestParam(value = "fileImage", required = false) MultipartFile multipartFile
) throws IOException {
    if (multipartFile != null && !multipartFile.isEmpty()) {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        questions.setImage(fileName);
        Question savedQuestion = this.question.save(questions);

        String uploadDir = "./questionbank/" + savedQuestion.getId();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save upload file: " + fileName, e);
        }
    } else {
        Question existingQuestion = this.question.findById(questions.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid question Id:" + questions.getId()));
        questions.setImage(existingQuestion.getImage());
        this.question.save(questions);
    }

    return "redirect:/test/questionbank";
}

    @GetMapping("materials/editmaterial/{id}")
    public String getMaterial(Model model,@PathVariable int id) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapter> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
      Materials mater = material.findMaterialbyID(id);
        model.addAttribute("material",mater);
        return "manager/edit-material";
    }

    @PostMapping("/editmaterial")
    public String postMaterial(@ModelAttribute("material") Materials materials) {
        material.save(materials);
        return "redirect:/test/materials";
    }

    @GetMapping("/materials/{id}")
    public String deleteMaterial(@ModelAttribute Materials materials,@PathVariable Integer id) {
    material.deleteById(id);
    return "redirect:/test/materials";
    }

    @Transactional
    @GetMapping("/questionbank/{id}")
    public String deleteQuestion(@ModelAttribute Question questions,@PathVariable Integer id) {
        List<MockQuestion> mockque= mockquestion.MockTestByQuestion(id);

        MockQuestionKey key = new MockQuestionKey();
        if(mockque==null)
            question.deleteById(id);
        for(MockQuestion i: mockque){
            key.setMocktestid(i.getMockTest().getId());
            key.setQuestionid(i.getQuestion().getId());
            mockquestion.deleteById(key);
        }

        question.deleteById(id);


        return "redirect:/test/questionbank";
    }


    @GetMapping("/mockdetails/{id}")
    public String mockQuestion(Model model,@PathVariable Integer id) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapter> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        List<Question> questions = question.mockTestDetails(id);
        model.addAttribute("ques", questions);
        Question q = new Question();
        model.addAttribute("nques",q);
        MockTest nMockTest= mock.mocktestbyID(id);
        model.addAttribute("mocktest",nMockTest);

        return "manager/mockquestion";
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

        return "redirect:/test/mockdetails/"+id;
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
        List<Chapter> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Materials> mater = material.findMaterialsbySubject(getUserID());
        if(keyword!=null){
            mater = material.searchMaterial(getUserID(),keyword);
        }
        model.addAttribute("material",mater);
        return "manager/Materiallist";

    }
    @PostMapping("/materials")
    public String getMaterials(@ModelAttribute Materials materials){
        material.save(materials);
        return "redirect:/test/materials";
    }
   // @Autowired
   @Transactional
   @GetMapping("/mocktests/{id}")
   public String deleteMockTest(@ModelAttribute MockTest mocktest,@PathVariable("id") Integer id) {
       List<MockQuestion> mockque = mockquestion.mockDetails(id);

       MockQuestionKey key = new MockQuestionKey();
       if (mockque != null && !mockque.isEmpty()) {
           for (MockQuestion i : mockque) {
               key.setMocktestid(i.getMockTest().getId());
               key.setQuestionid(i.getQuestion().getId());

               question.deleteById(i.getQuestion().getId());
               // Xóa MockQuestion trước
               mockquestion.deleteById(key);

           }
       }

       // Xóa MockTest cuối cùng
       mock.deleteById(id);
       return "redirect:/test/mocktests";
   }
    @GetMapping("/mocktests")
    public String getMockTests(Model model,@ModelAttribute Manager manager ) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);

        // ghep code thi sua
        //List<MockTest> mockTests = mock.findMockTestByUserId(manager.getSubjectid());
        List<MockTest> mockTests = mock.findMockTestByUserId(getUserID());

        model.addAttribute("mock", mockTests);

        return "manager/list";
    }
   // private final String uploadDir = "D:\\helpless\\src\\main\\resources\\excels";

    private String uploadDir = "C:\\src\\main\\resources\\excels";

    public String storeFile(MultipartFile file) throws IOException {
        // Tạo thư mục nếu chưa tồn tại
        Path uploadPath = Paths.get(uploadDir);
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }

        // Lưu file vào thư mục cấu hình
        Path filePath = uploadPath.resolve(uploadDir);
        Files.copy(file.getInputStream(), filePath);


        return file.getOriginalFilename();
    }
     @PostMapping("/mocktests")
    public String postMockTests(@ModelAttribute MockTest mockTest,@Param("file") String file) throws IOException {
        mock.save(mockTest);

         try {
             // Lưu file vào thư mục cấu hình và lấy tên file
//             String fileName = storeFile(file);
//
//             String excelPath = "C:\\src\\main\\resources\\excels" + fileName;

             //String excelPath = "C:\\Users\\Admin\\Desktop\\demo-project-1\\src\\main\\resources\\excels" + fileName;
             //ExcelHelper excelHelper = new ExcelHelper(excelPath);

             // readdata and save db
             List<Question> list = readExcel(file);
             for (Question questionss : list) {
                 questionss.setSubjectID(mockTest.getSubjectId());
                 questionss.setStatus(0);
                 question.save(questionss);
                 MockTest nMockTest = mock.mocktestbyID(mockTest.getId());
                 MockQuestionKey mockQuestionKey = new MockQuestionKey(nMockTest.getId(), questionss.getId());
                 MockQuestion mockques = new MockQuestion();
                 mockques.setId(mockQuestionKey);
                 mockques.setMockTest(nMockTest);
                 mockques.setQuestion(questionss);
                 mockquestion.save(mockques);
                // deleteAllFilesInDirectory("D:\\helpless\\src\\main\\resources\\excels");
             }
         } catch(Exception e){
             e.printStackTrace();
         }
        return "redirect:/test/mocktests";
    }

    @GetMapping("/changepassword")
    public  String changepass(Model model){
        model.addAttribute("change", new ChangePass());
        return "manager/changepassword";
    }


    @PostMapping("/changepassword")
    public String changePassWord(@ModelAttribute("change") ChangePass change,Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user= userRepository.findUserByUsername(name);

        if(!user.getPassword().equals("{noop}"+change.getOldpassword())){
            model.addAttribute("error","Incorrect Password");
            return "manager/changepassword";
        }
        else
            if(!change.getNewpassword().equals(change.getReenter())){
                model.addAttribute("error","Password does not match");
                return "manager/changepassword";
            }
        user.setPassword("{noop}"+change.getNewpassword());
        userRepository.save(user);
        return "redirect:/test/homepage";
    }

    @GetMapping("/questionrequest")
    public String requestQuestion(Model model){
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapter> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        List<Question> ques = question.questionByStatus(getUserID(),2);
        model.addAttribute("ques",ques);
        return "manager/question-request";
    }
    @PostMapping("/questionrequest")
    public String postRequestQuestion(@RequestParam("id") Integer id,Model model,@RequestParam("check") String check){

        Question question1 = question.findQuestionBYID(id);
        if(check.equals("approve")){
            question1.setStatus(0);
            question.save(question1);
        }
        else if(check.equals("cancel")){
            question1.setStatus(1);

        }
        System.out.println(check);

                question.save(question1);


        return "redirect:/test/questionrequest";
    }

    // Analysis
    @Autowired
    private ChartService chartService;
    @GetMapping("/analysis/{id}")
    public String analysis(Model model,@PathVariable Integer id,@Param("filter") Integer filter,HttpServletResponse response
    ) throws IOException {
        List<TakenMockTest> list = takenMockTestRepository.takenTestByMockTestID(id);
        if (filter != null) {
            switch (filter) {
                case 1:
                    list = takenMockTestRepository.takenTestByScore(id, 0, 5);
                    break;
                case 2:
                    list = takenMockTestRepository.takenTestByScore(id, 5, 8);
                    break;
                case 3:
                    list = takenMockTestRepository.takenTestByScore(id, 8, 10);
                    break;
                case 4:
                    list = takenMockTestRepository.takenTestByMockTestID(id);
                    break;
                default:
                    break;
            }
        }
        if(list.isEmpty()){
            model.addAttribute("error","No result");
        }
        model.addAttribute("mock",list);
        List<User> student = userRepository.findAll();
        model.addAttribute("student",student);
        int cnt1=0;

        List<TakenMockTest> list1 = takenMockTestRepository.takenTestByScore(id, 0, 5);
        for(int i=0;i<list1.size();i++){
            cnt1++;
        }

        model.addAttribute("cnt1",cnt1);
        int cnt2=0;

        List<TakenMockTest> list2 = takenMockTestRepository.takenTestByScore(id, 5, 8);
        for(int i=0;i<list2.size();i++){
            cnt2++;
        }
        model.addAttribute("cnt2",cnt2);

        int cnt3=0;
        List<TakenMockTest> list3 = takenMockTestRepository.takenTestByScore(id, 8, 10);
        for(int i=0;i<list3.size();i++){
            cnt3++;
        }
        model.addAttribute("cnt3",cnt3);

        String pieChartBase64 = chartService.generatePieChart(cnt1, cnt2, cnt3);
        model.addAttribute("pieChartBase64", pieChartBase64);
        return "manager/analysis";
    }
// export file excel

//    @GetMapping("/export/{id}")
//    public ResponseEntity<byte[]> exportToExcel(@PathVariable("id") Integer id) throws IOException {
//        System.out.println(id);
//        List<TakenMockTest> taken = takenMockTestRepository.takenTestByMockTestID(id);
//
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("StudentScore");
//
//        Row header = sheet.createRow(0);
//        header.createCell(0).setCellValue("Student ID");
//        header.createCell(1).setCellValue("Score");
//
//        int rowNum = 1;
//        for (TakenMockTest test : taken) {
//            Row row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(test.getUserID());
//            row.createCell(1).setCellValue(test.getScore());
//        }
//
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        workbook.write(bos);
//        byte[] bytes = bos.toByteArray();
//        workbook.close();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=studentscore.xlsx");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .body(bytes);
//    }


@Autowired
private ExcelService excelService;
    @GetMapping("/export/{mocktestid}")
    public void generateExcelReport(HttpServletResponse response, @PathVariable("mocktestid") int mocktestid,Model model) throws IOException {
        response.setContentType("application/octet-stream");
        List<TakenMockTest> list = takenMockTestRepository.takenTestByMockTestID(mocktestid);
        model.addAttribute("mock",list);
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=score.xls";
        response.setHeader(headerKey, headerValue);
        excelService.generateExcel(response,mocktestid);
    }


}
