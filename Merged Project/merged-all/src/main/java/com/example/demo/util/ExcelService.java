package com.example.demo.util;

import com.example.demo.data.*;
import com.example.demo.entity.Class;
import com.example.demo.entity.*;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

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

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Score table");
            HSSFRow row = sheet.createRow(0);

            row.createCell(0).setCellValue("Name");
            row.createCell(1).setCellValue("Class");
            row.createCell(2).setCellValue("Score");


            int dataRowIndex = 1;

            for (TakenMockTest takenMockTest : listScore) {
                User user = userRepository.findUserById(takenMockTest.getStudent().getUserId());
                Student classcode = studentRepository.findStudentById(takenMockTest.getStudent().getUserId());
                Class classname = classRepository.findClassById(classcode.getSclass().getClassCode());
                if (user != null) {
                    HSSFRow dataRow = sheet.createRow(dataRowIndex);
                    dataRow.createCell(0).setCellValue(user.getFullname());
                    dataRow.createCell(1).setCellValue(classname.getClassName());
                    dataRow.createCell(2).setCellValue(takenMockTest.getScore());

                    dataRowIndex++;
                }
            }

            ServletOutputStream ops = response.getOutputStream();
            workbook.write(ops);
            workbook.close();
            ops.close();
        }


    }

