package com.example.demo.helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelHelper {
    private List<String> fieldNames = Arrays.asList("questiontitle", "image", "option1", "option2", "option3", "option4", "answer", "chapterid", "levelid");
    private Workbook workbook = null;
    private String filePath;

    public ExcelHelper(String filePath) {
        this.filePath = filePath;
        initialize();
    }

    private void initialize() {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String>> readData() throws IOException {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            List<String> headers = new ArrayList<>();

            if (rowIterator.hasNext()) {
                Row headerRow = rowIterator.next();
                for (Cell cell : headerRow) {
                    headers.add(cell.getStringCellValue());
                }
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                boolean isEmptyRow = true;
                Map<String, String> dataMap = new HashMap<>();

                for (int i = 0; i < headers.size(); i++) {
                    if (fieldNames.contains(headers.get(i))) {
                        Cell cell = row.getCell(i);
                        if (cell != null && cell.getCellType() != CellType.BLANK) {
                            isEmptyRow = false;
                            dataMap.put(headers.get(i), cell.toString());
                        } else {
                            dataMap.put(headers.get(i), "");
                        }
                    }
                }

                if (!isEmptyRow) {
                    dataList.add(dataMap);
                }
            }
        }
        return dataList;
    }
}
