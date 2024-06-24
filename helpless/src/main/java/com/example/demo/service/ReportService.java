package com.example.demo.service;

import com.example.demo.data.TakenMocktestRepository;
import com.example.demo.entity.TakenMockTest;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private TakenMocktestRepository takenMocktestRepository;

    public void generateExcel(HttpServletResponse response, int classcode, int mocktestid ) throws IOException {

        List<TakenMockTest> listScore = takenMocktestRepository.listScore(classcode, mocktestid);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Score table");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Name");
        row.createCell(1).setCellValue("Score");

        int dataRowIndex = 1;

        for(TakenMockTest i: listScore) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(i.getStudent().getUser().getFullname());
            dataRow.createCell(1).setCellValue(i.getScore());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }


}
