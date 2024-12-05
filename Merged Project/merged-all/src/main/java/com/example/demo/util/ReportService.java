package com.example.demo.util;

import com.example.demo.data.ChapterRepository;
import com.example.demo.data.LevelRepository;
import com.example.demo.data.TakenMockTestRepository;
import com.example.demo.entity.Chapter;
import com.example.demo.entity.Level;
import com.example.demo.entity.TakenMockTest;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private TakenMockTestRepository takenMocktestRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private LevelRepository levelRepository;

    public void generateExcel(HttpServletResponse response, int classcode, int mocktestid ) throws IOException {
        List<TakenMockTest> listScore = takenMocktestRepository.listScore(classcode, mocktestid);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Score table");
        XSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Name");
        row.createCell(1).setCellValue("Score");

        int dataRowIndex = 1;

        for(TakenMockTest i: listScore) {
            XSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(i.getStudent().getUser().getFullname());
            dataRow.createCell(1).setCellValue(i.getScore());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }

    public void generateTemplate(HttpServletResponse response, int subjectid) throws IOException {
        List<Chapter> chapterList = chapterRepository.findChapterBySubjectId(subjectid);
        List<Level> levelList = levelRepository.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);

        // Create a bold font
        XSSFFont boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Create a cell style with wrap text and bold font
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        style.setFont(boldFont);

        // Set column widths
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 4000);
        sheet.setColumnWidth(6, 4000);
        sheet.setColumnWidth(7, 4000);
        sheet.setColumnWidth(8, 4000);
        sheet.setColumnWidth(12, 4000);
        sheet.setColumnWidth(15, 4000);

        // Apply style to header row
        createStyledCell(row, 0, "questiontitle", style);
        createStyledCell(row, 1, "image", style);
        createStyledCell(row, 2, "option1", style);
        createStyledCell(row, 3, "option2", style);
        createStyledCell(row, 4, "option3", style);
        createStyledCell(row, 5, "option4", style);
        createStyledCell(row, 6, "answer", style);
        createStyledCell(row, 7, "chapterid", style);
        createStyledCell(row, 8, "levelid", style);

        XSSFCell cell4 = row.createCell(12);
        cell4.setCellValue("Chapter");
        XSSFCell cell5 = row.createCell(13);
        cell5.setCellValue("Chapter ID");
        int dataRowIndex = 1;

        for (Chapter i : chapterList) {
            XSSFRow dataRow = sheet.createRow(dataRowIndex);
            XSSFCell cell0 = dataRow.createCell(12);
            cell0.setCellValue(i.getName());
            cell0.setCellStyle(style);
            XSSFCell cell1 = dataRow.createCell(13);
            cell1.setCellValue(i.getId());
            cell1.setCellStyle(style);
            dataRowIndex++;
        }

        XSSFRow dataRow1 = sheet.createRow(dataRowIndex++);
        XSSFRow dataRow2 = sheet.createRow(dataRowIndex++);
        XSSFCell cell2 = dataRow2.createCell(12);
        cell2.setCellValue("Level Name");
        XSSFCell cell3 = dataRow2.createCell(13);
        cell3.setCellValue("Level ID");


        for (Level level : levelList) {
            XSSFRow dataRow = sheet.createRow(dataRowIndex);
            XSSFCell cell0 = dataRow.createCell(12);
            cell0.setCellValue(level.getName());
            cell0.setCellStyle(style);
            XSSFCell cell1 = dataRow.createCell(13);
            cell1.setCellValue(level.getId());
            cell1.setCellStyle(style);
            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }

    private void createStyledCell(XSSFRow row, int column, String value, CellStyle style) {
        XSSFCell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
}
