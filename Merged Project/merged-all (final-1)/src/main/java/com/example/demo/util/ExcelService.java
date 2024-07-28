package com.example.demo.util;

import com.example.demo.data.*;
import com.example.demo.entity.Class;
import com.example.demo.entity.*;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.List;

@Service
public class ExcelService {

    TeacherRepository tr;
    ChapterRepository cr;
    LevelRepository lr;

    @Autowired
    public ExcelService(TeacherRepository tr, ChapterRepository cr, LevelRepository lr) {
        this.tr = tr;
        this.cr = cr;
        this.lr = lr;
    }

    public void createExcel() throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        int subjectid = tr.findSubjectIdByUsername(name);
        // Đường dẫn đến file Excel cần tạo
        Path path = Paths.get("static/questionTemplate.xlsx");
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
            List<Chapter> listChapter = cr.findChapterBySubject(subjectid);
            List<Level> listLevel = lr.findAll();

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

    public void exportToExcel(HttpServletResponse response, List<String> data) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Data");

        int rowNum = 1;
        for (String rowData : data) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(rowData);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }



    @Autowired
    private TakenMockTestRepository takenMocktestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;

    public void generateExcel(HttpServletResponse response, int mocktestid) throws IOException {

        System.out.println(mocktestid);
        List<TakenMockTest> listScore = takenMocktestRepository.takenTestByMockTestID2(mocktestid);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Score table");
        XSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Name");
        row.createCell(1).setCellValue("Class");
        row.createCell(2).setCellValue("Score");
        row.createCell(3).setCellValue("StartTime");
        row.createCell(4).setCellValue("EndTime");


        int dataRowIndex = 1;

        for (TakenMockTest takenMockTest : listScore) {
            User user = userRepository.findUserById(takenMockTest.getStudent().getUserId());
            Student classcode = studentRepository.findStudentById(takenMockTest.getStudent().getUserId());
            Class classname = classRepository.findClassById(classcode.getSclass().getClassCode());
            if (user != null) {
                XSSFRow dataRow = sheet.createRow(dataRowIndex);
                dataRow.createCell(0).setCellValue(user.getFullname());
                dataRow.createCell(1).setCellValue(classname.getClassName());
                dataRow.createCell(2).setCellValue(takenMockTest.getScore());
                dataRow.createCell(3).setCellValue((takenMockTest.getStarttime()==null) ? "" : takenMockTest.getStarttime().toString());
                dataRow.createCell(4).setCellValue((takenMockTest.getEndtime()==null) ? "" : takenMockTest.getEndtime().toString());
                dataRowIndex++;
            }
        }

//        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-Disposition", "attachment; filename=score.xlsx");
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        ops.flush();
        workbook.close();
        ops.close();
    }
}

