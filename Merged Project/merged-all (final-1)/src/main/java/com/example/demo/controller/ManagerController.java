package com.example.demo.controller;

import com.example.demo.data.*;
import com.example.demo.entity.*;
import com.example.demo.util.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.*;

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

    private Integer checkChapter(String chapterName){
        return chapter.getChapterIDByName(chapterName);
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

    public List<Question> readExcel(MultipartFile file) throws IOException {
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
            }
        }
        catch (Exception e) {
            throw new IOException("Có lỗi xảy ra khi đọc tệp Excel. Vui lòng kiểm tra lại định dạng tệp và thử lại.", e);
        }

        return questionsList;
    }

    public void createExcel() throws IOException {
        // Đường dẫn đến file Excel cần tạo
        Path path = Paths.get("target/classes/static/questionTemplate.xlsx");
        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Dropdown Sheet");

            // lam header
            Row headerRow = sheet.createRow(0);
            Cell cell1 = headerRow.createCell(0);
            cell1.setCellValue("Title");
            Cell cell3 = headerRow.createCell(2);
            cell3.setCellValue("Option 1");
            Cell cell4 = headerRow.createCell(3);
            cell4.setCellValue("Option 2");
            Cell cell5 = headerRow.createCell(4);
            cell5.setCellValue("Option 3");
            Cell cell6 = headerRow.createCell(5);
            cell6.setCellValue("Option 4");
            Cell cell7 = headerRow.createCell(6);
            cell7.setCellValue("Answer");
            Cell cell8 = headerRow.createCell(7);
            cell8.setCellValue("Chapter");
            Cell cell9 = headerRow.createCell(8);
            cell9.setCellValue("Level");

            // data
            Subject sub = subject.findSubjectByUserID(getUserID());
            List<Chapter> listChapter = chapter.findChapterBySubject(sub.getSubjectId());
            List<Level> listLevel = level.findAll();

            int sheetIndex = workbook.getSheetIndex("HiddenSheet");
            if (sheetIndex != -1) {
                workbook.removeSheetAt(sheetIndex);
            }

            // sheet an
            Sheet hiddenSheet = workbook.createSheet("HiddenSheet");
            String[] listChapterArr = new String[listChapter.size()];
            String[] listLevelArr = new String[listLevel.size()];

            //ghi vao sheet an
            for (int i = 0; i < listChapter.size(); i++) {
                Row row = hiddenSheet.createRow(i);
                Cell cell = row.createCell(0);
                cell.setCellValue(listChapter.get(i).getName());
                listChapterArr[i] = listChapter.get(i).getName();

            }
            for(int i=0;i<listLevel.size();i++){
                Row row = hiddenSheet.createRow(listChapter.size()+i);
                Cell cell = row.createCell(0);
                cell.setCellValue(listLevel.get(i).getName());
                listLevelArr[i]=listLevel.get(i).getName();
            }
            // tao droplist

            DataValidationHelper validationHelper = sheet.getDataValidationHelper();
            String formula = "HiddenSheet!$A$1:$A$" + listChapter.size();
            String formulaLevel = "HiddenSheet!$A$"+(listChapter.size()+1)+":$A$" + listChapter.size()+1+listLevel.size();
            DataValidationConstraint constraint = validationHelper.createFormulaListConstraint(formula);
            DataValidationConstraint constraintLevel = validationHelper.createFormulaListConstraint(formulaLevel);

            // o can droplist
            CellRangeAddressList addressList = new CellRangeAddressList(1, 10000, 7, 7);
            CellRangeAddressList addressListLevel = new CellRangeAddressList(1, 10000, 8, 8);


            DataValidation dataValidation = validationHelper.createValidation(constraint, addressList);
            DataValidation dataValidationLevel = validationHelper.createValidation(constraintLevel, addressListLevel);

            dataValidation.setShowErrorBox(true); // Hiển thị hộp lỗi nếu giá trị không hợp lệ

            sheet.addValidationData(dataValidation);
            sheet.addValidationData(dataValidationLevel);

            workbook.setSheetHidden(workbook.getSheetIndex(hiddenSheet), true);

            // Ghi workbook vào file
            File file = path.toFile();
            file.getParentFile().mkdirs();

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Excel file created successfully at " + path);
        }
    }

    @GetMapping("/homepage")
    public String listMockTest(Model model, @Param("keyword") String keyword) throws IOException {
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
        System.out.println("Exception here!");
        return question.findQuestionByManagerId(getUserID(),pageable);
    }

    @GetMapping("/questionbank")
    public String questionbank(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               Model model,
                               @Param("keyword")String keyword,
                               @RequestParam(value="filter", required = false) Integer chapterID) throws IOException {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapter> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
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
        createExcel();
        return "manager/questionbank";
    }

    @PostMapping("/questionbank")
    public String getQuestion(@ModelAttribute(name="ques") Question question,
                              @RequestParam(value = "file", required = false) String file,
                              @RequestParam(value = "fileImage", required = false) MultipartFile multipartFile,
                              @RequestParam(value = "fileExcel", required = false) MultipartFile multipartFileExcel,
                              Model model) throws IOException {

        if(question.getQuestiontitle() != null && !question.getQuestiontitle().isEmpty()){
            this.question.save(question);
        }

        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            question.setImage(fileName);
            Question saveQues = this.question.save(question);

            String uploadDir = "./questionbank/" + saveQues.getQuestionid();
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsoluteFile());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save upload file: " + fileName, e);
            }
        }

        if (multipartFileExcel != null && !multipartFileExcel.isEmpty()) {
            String fileNameExcel = StringUtils.cleanPath(multipartFileExcel.getOriginalFilename());
            if (!fileNameExcel.endsWith(".xlsx")) {
                throw new IOException("Định dạng tệp không hợp lệ. Vui lòng tải lên tệp Excel (.xlsx).");
            }

            List<Question> list = readExcel(multipartFileExcel);
            for (Question questions : list) {
                questions.setStatus(1);
                questions.setSubjectid(question.getSubjectid());
                this.question.save(questions);
            }
            String uploadDir = "./questionbank/" + fileNameExcel;
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = multipartFileExcel.getInputStream()) {
                Path filePath = uploadPath.resolve(fileNameExcel);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save upload file: " + fileNameExcel, e);
            }

        }

        return "redirect:/test/questionbank";
    }

    @GetMapping("questionbank/editquestion/{id}")
    public String list1(Model model,@PathVariable int id) {
      Question questions = question.findQuestionById(id);
        model.addAttribute("ques", questions);
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapter> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        return "manager/edit-question";
    }

    @PostMapping("/editquestion")
    public String postQuestion(@ModelAttribute("ques") Question questions,
                               @RequestParam(value = "fileImage", required = false) MultipartFile multipartFile
    ) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            questions.setImage(fileName);
            Question savedQuestion = this.question.save(questions);

            String uploadDir = "./questionbank/" + savedQuestion.getQuestionid();
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                System.out.println("Attempting to save image at: " + filePath);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save upload file: " + fileName, e);
            }
        } else {
            Question existingQuestion = this.question.findById(questions.getQuestionid()).orElseThrow(() -> new IllegalArgumentException("Invalid question Id:" + questions.getQuestionid()));
            questions.setImage(existingQuestion.getImage());
            this.question.save(questions);
        }

        return "redirect:/test/questionbank";
    }

  // Materials

    @GetMapping("/materials")
    public String materrialList(Model model,
                                @Param("keyword") String keyword) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapter> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Material> mater = material.findMaterialsByManager(getUserID());
        if(keyword!=null){
            mater = material.searchMaterial(getUserID(),keyword);
        }
        model.addAttribute("material",mater);
        return "manager/Materiallist";
    }

    @PostMapping("/materials")
    public String getMaterials(@ModelAttribute(name = "material") Material materials,
                               @RequestParam(value = "contentFile", required = false) MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        materials.setContent(fileName);
        materials.setChapter(chapter.getChapterByChapterId(materials.getChapter().getId()));
        materials.setSubject(subject.findSubjectById(materials.getSubject().getSubjectId()));
        Material saveMaterial = material.save(materials);
        String uploadDir = "./materials/" + saveMaterial.getMaterialid();
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath.toFile().getAbsoluteFile());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save uploaded file: " + fileName, e);
        }

        return "redirect:/test/materials";
    }

    @GetMapping("materials/editmaterial/{id}")
    public String getMaterial(Model model,@PathVariable int id) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapter> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        Material mater = material.findMaterialbyID(id);
        model.addAttribute("material",mater);
        return "manager/edit-material";
    }

    @PostMapping("/editmaterial")
    public String postMaterial(@ModelAttribute("material") Material materials,
                               @RequestParam(value = "contentFile", required = false) MultipartFile multipartFile) throws IOException {
        System.out.println("Material to edit: " + materials);
        materials.setSubject(subject.findSubjectById(materials.getSubject().getSubjectId()));
        materials.setChapter(chapter.getChapterByChapterId(materials.getChapter().getId()));
        if(multipartFile!=null&& !multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            materials.setContent(fileName);
            Material saveMaterial = material.save(materials);
            String uploadDir = "./materials/" + saveMaterial.getMaterialid();
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsoluteFile());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save uploaded file: " + fileName, e);
            }
        }
        else {
            Material existingMaterials = material.findById(materials.getMaterialid()).orElseThrow(() -> new IllegalArgumentException("Invalid materials Id:" + materials.getMaterialid()));
            materials.setContent(existingMaterials.getContent());
            materials.setSubject(subject.findSubjectById(materials.getSubject().getSubjectId()));
            material.save(materials);
        }
        return "redirect:/test/materials";
    }


    @GetMapping("/materials/{id}")
    public String deleteMaterial(@ModelAttribute Material materials,@PathVariable Integer id) {
        material.deleteById(id);
        return "redirect:/test/materials";
    }
// End materials

    @Transactional
    @GetMapping("/questionbank/{id}")
    public String deleteQuestion(@ModelAttribute Question questions,@PathVariable Integer id) {
        List<MockQuestion> mockque= mockquestion.MockTestByQuestion(id);

        MockQuestionKey key = new MockQuestionKey();
        if(mockque==null)
            question.deleteById(id);
        for(MockQuestion i: mockque){
            key.setMocktestid(i.getMockTest().getMocktestid());
            key.setQuestionid(i.getQuestion().getQuestionid());
            mockquestion.deleteById(key);
        }

        question.deleteById(id);

        return "redirect:/test/questionbank";
    }

// MockTest Details
    @GetMapping("/mockdetails/{id}")
    public String mockQuestion(Model model,@PathVariable Integer id,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "0") int page) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        List<Chapter> chapters = chapter.findChapterBySubject(subjects.getSubjectId());
        model.addAttribute("chapter",chapters);
        List<Level> levels = level.findAll();
        model.addAttribute("level", levels);
        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero");
        }
        Page<Question> questions = question.mockTestDetails(id,PageRequest.of(page,size));
        if(questions.isEmpty()||questions== null){
            model.addAttribute("empty","No Question");
        }
        model.addAttribute("test",id);
        model.addAttribute("ques", questions);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", questions.getTotalPages());
        model.addAttribute("size", size);
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
            key.setMocktestid(i.getMockTest().getMocktestid());
            key.setQuestionid(i.getQuestion().getQuestionid());
            mockquestion.deleteById(key);
        }

        question.deleteById(questionid);

        return "redirect:/test/mockdetails/"+id;
    }

    @PostMapping("/mockdetails/{id}")
    public String addMockquestion(@ModelAttribute("nques") Question questions,
                                  @PathVariable("id") Integer id,
                                  @RequestParam(value="fileImage",required = false) MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        questions.setImage(fileName);

        Question saveQues= this.question.save(questions);

        String uploadDir ="./questionbank/"+ saveQues.getQuestionid();
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

        MockTest nMockTest= mock.mocktestbyID(id);
        MockQuestionKey mockQuestionKey = new MockQuestionKey(nMockTest.getMocktestid(), questions.getQuestionid());

        MockQuestion mockques = new MockQuestion();
        mockques.setId(mockQuestionKey);
        mockques.setMockTest(nMockTest);
        mockques.setQuestion(questions);

        System.out.println("\n=== New MockQuestion: " + mockques);
        mockquestion.save(mockques);
        return "redirect:/test/mockdetails/" + id;
    }

// Mocktest
   @Transactional
   @GetMapping("/mocktests/{id}")
   public String deleteMockTest(@PathVariable("id") Integer id) {
       System.out.println(id);
       List<MockQuestion> mockQuestions = mockquestion.mockDetails(id);
       MockQuestionKey key = new MockQuestionKey();
        List<Integer> listID = new ArrayList<>();
           for (MockQuestion mockQuestion : mockQuestions) {
                   Integer qID= mockQuestion.getQuestion().getQuestionid();
                   listID.add(qID);
               key.setMocktestid(mockQuestion.getMockTest().getMocktestid());
               key.setQuestionid(mockQuestion.getQuestion().getQuestionid());
               System.out.println("questionID:"+qID+"+++++");
               System.out.println(key.toString());
              mockquestion.deleteById(key);
           }
           for(Integer i: listID){
               System.out.println("ListD: "+i);
               question.deleteById(i);
           }

       List<TakenMockTest> listT= takenMockTestRepository.takenTestByMockTestID(id);
           for (TakenMockTest i: listT){
               System.out.println(i.toString());
               takenMockTestRepository.delete(i);
           }
//       // Xóa MockTest cuối cùng
      mock.deleteById(id);
       return "redirect:/test/mocktests";
   }

    @GetMapping("/mocktests")
    public String getMockTests(Model model,@ModelAttribute Manager manager ) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);

        List<MockTest> mockTests = mock.findMockTestByUserIdOrder(getUserID());

        model.addAttribute("mock", mockTests);

        return "manager/list";
    }

    @PostMapping("/mocktests")
    public String postMockTests(@ModelAttribute MockTest mockTest,
                                @RequestParam(value = "fileExcel", required = false) MultipartFile multipartFile,
                                Model model) throws IOException {
         Subject managedSubject = subject.findById(mockTest.getSubject().getSubjectId())
                 .orElseThrow(() -> new IllegalArgumentException("Invalid subject ID"));
         mockTest.setSubject(managedSubject);
         MockTest savedMockTest = mock.save(mockTest);
         if (multipartFile != null && !multipartFile.isEmpty()) {
             String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

             if (!multipartFile.getOriginalFilename().endsWith(".xlsx")) {
                 throw new IOException("Invalid file format. Please upload Excel files (.xlsx) only.");
             }
             List<Question> list = readExcel(multipartFile);

             for (Question questionss : list) {
                 System.out.println(questionss.toString());
                 questionss.setSubjectid(mockTest.getSubject().getSubjectId());
                 questionss.setStatus(0);
                 question.save(questionss);
                 MockTest nMockTest = mock.mocktestbyID(mockTest.getMocktestid());
                 MockQuestionKey mockQuestionKey = new MockQuestionKey(
                         nMockTest.getMocktestid(), questionss.getQuestionid());
                 MockQuestion mockques = new MockQuestion();
                 mockques.setId(mockQuestionKey);
                 mockques.setMockTest(nMockTest);
                 mockques.setQuestion(questionss);
                 mockquestion.save(mockques);
                 String uploadDir = "./mocktest/" + savedMockTest.getMocktestid();
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
             }
             // Xóa thư mục và toàn bộ nội dung bên trong
             String directoryToDelete = "./mocktest/" + savedMockTest.getMocktestid();
             try {
                 FileUtils.deleteDirectory(new File(directoryToDelete));
             } catch (IOException e) {
                 throw new IOException("Could not delete directory: " + directoryToDelete, e);
             }
         }
         return "redirect:/test/mocktests";
    }

    @GetMapping("mocktests/editmocktest/{id}")
    public String getEditMockTest(Model model,@PathVariable int id) {
        Subject subjects = subject.findSubjectByUserID(getUserID());
        model.addAttribute("subject",subjects);
        MockTest mockTest = mock.mocktestbyID(id);
        model.addAttribute("mocktest",mockTest);
        return "manager/edit-mocktest";
    }

    @PostMapping("/editmocktest")
    public String postEditMocktest(@ModelAttribute("mocktest") MockTest mockTest) {
        mockTest.setSubject(subject.findSubjectById(mockTest.getSubject().getSubjectId()));
        mock.save(mockTest);
        return "redirect:/test/mocktests";
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
        System.out.println("List of pending questions: " + ques);
        model.addAttribute("ques",ques);
        return "manager/question-request";
    }

//    @PostMapping("/questionrequest")
//    public String postRequestQuestion(@RequestParam(value = "id", required = false) Integer id,Model model,@RequestParam("check") String check){
//        System.out.println("Approved/Denied question ID: " + id);
//        Question question1 = question.findQuestionById(id);
//        if(check.equals("approve")){
//            question1.setStatus(0);
//            question.save(question1);
//        }
//        else if(check.equals("cancel")){
//            question1.setStatus(3);
//        }
//        System.out.println(check);
//                question.save(question1);
//        return "redirect:/test/questionrequest";
//    }

    @GetMapping("/processrequest")
    public String postRequestQuestion(@RequestParam(value = "id", required = false) Integer id,Model model,@RequestParam("check") String check){
        System.out.println("Approved/Denied question ID: " + id);
        Question question1 = question.findQuestionById(id);
        if(check.equals("approve")){
            question1.setStatus(0);
            question.save(question1);
        }
        else if(check.equals("cancel")){
            question1.setStatus(3);
        }
        System.out.println(check);
        question.save(question1);
        return "redirect:/test/questionrequest";
    }

    // Analysis
    @Autowired
    private ChartService chartService;
    @GetMapping("/analysis/{id}")
    public String analysis(Model model,@PathVariable Integer id,@Param("filter") Integer filter,HttpServletResponse response) throws IOException {
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
        else{
            model.addAttribute("error",null);
        }
        list.sort(Comparator.comparingDouble(TakenMockTest::getScore));
        model.addAttribute("mock",list);
        model.addAttribute("mockTestID",id);
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

    @Autowired
    private ExcelService excelService;

    @GetMapping("/export/{mocktestid}")
    public void generateExcelReport(HttpServletResponse response, @PathVariable("mocktestid") int mocktestid,Model model) throws IOException {
        response.setContentType("application/octet-stream");
        List<TakenMockTest> list = takenMockTestRepository.takenTestByMockTestID(mocktestid);
        model.addAttribute("mock",list);
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=score.xlsx";
        response.setHeader(headerKey, headerValue);
        excelService.generateExcel(response,mocktestid);
    }
}